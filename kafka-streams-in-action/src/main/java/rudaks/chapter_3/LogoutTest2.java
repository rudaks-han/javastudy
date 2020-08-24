package rudaks;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import scala.collection.immutable.Stream;

@Slf4j
public class LogoutTest2 {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "logout");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        GlobalKTable<String, String> connect = builder.globalTable("connect");

        // we get a stream of user purchases
        KStream<String, String> disconnect = builder.stream("disconnect");

        // we want to enrich that stream
        KStream<String, String> connectDisconnectJoin =
            disconnect.leftJoin(connect,
                               (key, value) -> key, /* map from the (key, value) of this stream to the key of the GlobalKTable */
                               (userPurchase, userInfo) -> "Purchase=" + userPurchase + ",UserInfo=[" + userInfo + "]"
            );

        connectDisconnectJoin.to("connect-disconnect-join");


        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp(); // only do this in dev - not in prod
        streams.start();

        // print the topology
        System.out.println(streams.toString());

        // shutdown hook to correctly close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private static ProducerRecord<String, String> connectRecord(String key, String value){
        return new ProducerRecord<>("connect", key, value);
    }
}
