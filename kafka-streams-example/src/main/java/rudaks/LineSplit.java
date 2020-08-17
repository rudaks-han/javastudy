package rudaks;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class LineSplit {

    public static void main(String[] args) {
        Properties props = new Properties();
        //카프카 스트림즈 애플리케이션을 유일할게 구분할 아이디
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-linesplit");
        //스트림즈 애플리케이션이 접근할 카프카 브로커정보
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //데이터를 어떠한 형식으로 Read/Write할지를 설정(키/값의 데이터 타입을 지정) - 문자열
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //데이터의 흐름으로 구성된 토폴로지를 정의할 빌더
        final StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> source = builder.stream("streams-plaintext-input");
        source.flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .to("streams-linesplit-output");

        //최종적인 토폴로지 생성
        final Topology topology = builder.build();

        //만들어진 토폴로지 확인
        System.err.println("Topology info = " + topology.describe());

        final KafkaStreams streams = new KafkaStreams(topology, props);

        try {
            streams.start();
            System.out.println("topology started");
        } catch (Throwable e) {
            System.exit(1);
        }
        //System.exit(0);

        /**
         * ./kafka-console-producer.sh --broker-list localhost:9092 --topic streams-plaintext-input
         * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-linesplit-output --from-beginning
         */
    }
}
