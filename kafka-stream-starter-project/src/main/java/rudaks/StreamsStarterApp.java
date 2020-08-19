package rudaks;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Arrays;
import java.util.Properties;

/**
 * 1. Stream from Kafka                                 <null, "Kafka Kafka Streams">
 * 2. MapValues lowercase                               <null, "kafka kafka streams">
 * 3. FlatMapValues split by space                      <null, "kafka">, <null, "kafka">, <null, "streams">
 * 4. SelectKey to apply a key                          <"kafka", "kafka">, <"kafka", "kafka">, <"streams", "streams">
 * 5. GroupByKey before aggregation                     (<"kafka", "kafka">, <"kafka", "kafka">), (<"streams", "streams">)
 * 6. Count occurrences in each group                   <"kafka", 2>, <"streams", 1>
 * 7. To in order to writer the results back to Kafka
 */
public class StreamsStarterApp {

    public static void main(String[] args) {

        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        KStreamBuilder builder = new KStreamBuilder();

        // 1 - stream from kafka

        KStream<String, String> textLines = builder.stream("word-count-input");
        KTable<String, Long> wordCounts = textLines
                // 2 - map values to lowercase
                .mapValues(textLine -> textLine.toLowerCase())
                // can be alternatively written as:
                // .mapValues(String::toLowerCase)
                // 3 - flatmap values split by space
                .flatMapValues(textLine -> Arrays.asList(textLine.split("\\W+")))
                // 4 - select key to apply a key (we discard the old key)
                .selectKey((key, word) -> word)
                // 5 - group by key before aggregation
                .groupByKey()
                // 6 - count occurrences
                .count("Counts");

        // 7 - to in order to write the results back to kafka
        wordCounts.to(Serdes.String(), Serdes.Long(), "word-count-output");

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

        // shutdown hook to correctly close streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        // Update:
        // print the topology every 10 seconds for learning purposes
        while (true) {
            System.out.println(streams.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}


/*
./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic word-count-input
./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic word-count-output
 */