package rudaks;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

public class FavoriteColorApp {

    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "favorite-color-java");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:19092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        config.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");

        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, String> textLines = builder.stream("favourite-colour-input");

        KStream<String, String> usersAndColors = textLines
            .filter((key, value) -> value.contains(","))
            .selectKey((key, value) -> value.split(",")[0].toLowerCase())
            .mapValues(value -> value.split(",")[1].toLowerCase())
            .filter((user, color) -> Arrays.asList("green", "blue", "red").contains(color));

        usersAndColors.to("user-keys-and-colours");

        KTable<String, String> usersAndColorsTable = builder.table("user-keys-and-colours");

        KTable<String, Long> favoriteColors = usersAndColorsTable
            .groupBy((user, color) -> new KeyValue<>(color, color))
            .count("CountByColors");

        favoriteColors.to(Serdes.String(), Serdes.Long(), "favourite-colour-output");

        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

        System.out.println(streams.toString());

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
