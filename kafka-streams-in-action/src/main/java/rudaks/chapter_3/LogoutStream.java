package rudaks;

import java.util.Arrays;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;

@Slf4j
public class LogoutStream {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "logout");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        final KStream<String, String> disconnectStream = streamsBuilder.stream("command.buzzer.user-disconnect");
        final KStream<String, String> connectStream = streamsBuilder.stream("command.buzzer.user-connect");
        //final KStream<String, String> textLines = builder.stream(inputTopiclist);
        disconnectStream.transform(getTopicDetailsTransformer::new)
            .foreach(new ForeachAction<String, String>() {
                public void apply(String key, String value) {
                    System.out.println(key + ": " + value);
                }
            });
        disconnectStream.to("command.buzzer.user-disconnect-add");

        final KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);

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
}
