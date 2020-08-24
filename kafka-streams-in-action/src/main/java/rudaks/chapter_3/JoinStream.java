package rudaks;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;

@Slf4j
public class JoinStream {

    public static void main(String[] args) {
        Properties props = new Properties();
        //카프카 스트림즈 애플리케이션을 유일할게 구분할 아이디
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "logout");
        //스트림즈 애플리케이션이 접근할 카프카 브로커정보
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //데이터를 어떠한 형식으로 Read/Write할지를 설정(키/값의 데이터 타입을 지정) - 문자열
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        final Serde<String> stringSerde = Serdes.String();

        StreamsBuilder builder = new StreamsBuilder();
        /*final KStream<String, String> left = builder.stream("command.buzzer.user-disconnect");
        final KStream<String, String> right = builder.stream("command.buzzer.user-connect");*/
/*

        KStream<String, String> joined = left.leftJoin(right,
                                                         (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue, *//* ValueJoiner *//*
                                                              JoinWindows.of(TimeUnit.MINUTES.toMillis(5)),
                                                         Joined.with(
                                                                  stringSerde, *//* key *//*
                                                                  stringSerde,   *//* left value *//*
                                                                  stringSerde)  *//* right value *//*
        );

*/

        KStream<String, String> leftSource = builder.stream("command.buzzer.user-disconnect");
        KStream<String, String> rightSource = builder.stream("command.buzzer.user-connect");

        KStream<String, String> joined = leftSource.join(rightSource,
                                                         (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue,
                                                         JoinWindows.of(TimeUnit.MINUTES.toMillis(5)),
                                                         Joined.with(
                                                             Serdes.String(),
                                                             Serdes.String(),
                                                             Serdes.String())
        );

        /*
        KStream<String, UserInfo> leftSource = builder.stream("command.buzzer.user-connect");
        KTable<String, UserInfo> rightSource = builder.table("command.buzzer.user-disconnect");

        KStream<String, String> joined = leftSource.join(rightSource,
                                                         //(leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue
                                                         (leftValue, rightValue) -> (leftValue.toString() + rightValue.toString())
        );
*/


        joined.to("rudaks-add");

        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
        try {
            streams.start();
            System.out.println("topology started");
        } catch (Throwable e) {
            System.exit(1);
        }

        /**
         * ./kafka-console-producer.sh --broker-list localhost:9092 --topic streams-plaintext-input
         * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-linesplit-output --from-beginning
         */
    }

    private static class getTopicDetailsTransformer implements Transformer<String, String, KeyValue<String, String>> {

        private ProcessorContext context;

        @Override
        public void init(final ProcessorContext context) {
            this.context = context;
        }

        public KeyValue<String, String> transform(final String recordKey, final String recordValue) {

            //here i am returning key as topic name.
            return KeyValue.pair(context.topic(), recordValue);
        }

        @Override
        public void close() {
            // Not needed.
        }

    }

    @Data
    public static class UserInfo {
        private String sessionId;
        private String userId;
    }
}
