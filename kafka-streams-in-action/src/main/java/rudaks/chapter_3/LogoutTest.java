package rudaks;

import java.util.Arrays;
import java.util.Properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.support.serializer.JsonSerde;

@Slf4j
public class LogoutTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "logout");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        //props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);

        StreamsBuilder builder = new StreamsBuilder();
        final KStream<String, UserInfo> connectStream = builder.stream(
            "connect",
            Consumed.with(Serdes.String(), new JsonSerde<>(UserInfo.class))
        );
        

        /*KStream<String, UserInfo> userLogin = connectStream
            .selectKey((key, value) -> value.getSessionId());*/

        final KTable<String, Long> counts = connectStream
            .selectKey((key, value) -> value.getSessionId())
            .mapValues(value -> value.getSessionId())
            .groupBy((key, value) -> value)
            .count();


        counts.toStream().to("connect-out", Produced.with(Serdes.String(), Serdes.Long()));

        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }

}
