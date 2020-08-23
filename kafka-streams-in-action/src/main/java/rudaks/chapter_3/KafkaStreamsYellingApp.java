package rudaks.chapter_3;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class KafkaStreamsYellingApp {

    public static void main(String[] args) {
        Properties props = new Properties();

        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "yelling_app_id");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 1. StreamsConfig 인스턴스 생성
        StreamsConfig streamsConfig = new StreamsConfig(props);

        // 2. Serde 객체 생성
        Serde<String> stringSerde = Serdes.String();

        // 3. 처리 토폴로지 구성
        StreamsBuilder builder = new StreamsBuilder();
        /*KStream<String, String> simpleFirstStream = builder.stream("src-topic", Consumed.with(stringSerde, stringSerde));
        KStream<String, String> upperCasedStream = simpleFirstStream.mapValues(v -> v.toUpperCase());
        upperCasedStream.to("out-topic", Produced.with(stringSerde, stringSerde));*/

        KStream<String, String> simpleFirstStream = builder.stream("src-topic", Consumed.with(stringSerde, stringSerde));
        simpleFirstStream.mapValues(v -> v.toUpperCase())
                .to("out-topic", Produced.with(stringSerde, stringSerde));

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), streamsConfig);

        // 4. 카프카 스트림즈 프로그램 시작
        kafkaStreams.start();

        System.out.println("Shutting down the Yelling App now");
        //kafkaStreams.close();

        /*
        # topic create
        bin/kafka-topics.sh --create --zookeeper localhost:2181 --topic out-topic --replication-factor 1 --partitions 1

        # topic delete
        bin/kafka-topics.sh --delete --zookeeper localhost:2181 --topic out-topic

        # producer
        bin/kafka-console-producer.sh --broker-list localhost:9092 --topic src-topic

        # consumer
        bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic out-topic --from-beginning

        # 토픽이 삭제안되면 server.properties에 아래 추가
        delete.topic.enable=true


        kafka-topics.sh --describe --zookeeper localhost:2181 --topic out-topic
         */
    }
}
