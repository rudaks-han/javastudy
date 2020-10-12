package rudaks;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.kafka.support.serializer.JsonSerde;
import rudaks.model.UserDisconnect;
import rudaks.model.UserInfo;
import rudaks.processor.LogoutTimeoutProcessor;

public class LogoutChecker {

    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "logout-app");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1000");

        Topology topology = new Topology();
        String userDisconnectStateStore = "userDisconnectStore";

        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(userDisconnectStateStore);
        StoreBuilder<KeyValueStore<String, UserDisconnect>> storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, Serdes.String(), new JsonSerde<>(UserDisconnect.class));

        LogoutTimeoutProcessor logoutTimeoutProcessor = new LogoutTimeoutProcessor(userDisconnectStateStore, 1*1000);

        topology
            .addSource("connect-source", Serdes.String().deserializer(), new JsonSerde<>(UserInfo.class).deserializer(), "connect-topic")
            .addSource("disconnect-source", Serdes.String().deserializer(), new JsonSerde<>(UserInfo.class).deserializer(), "disconnect-topic")
            .addProcessor("logout-timeout-processor", () -> logoutTimeoutProcessor, "connect-source", "disconnect-source")
            .addStateStore(storeBuilder, "logout-timeout-processor")
            .addSink("logout-user-sink", "logout-topic", Serdes.String().serializer(), new JsonSerde<>(UserDisconnect.class).serializer(), "logout-timeout-processor");

        KafkaStreams streams = new KafkaStreams(topology, config);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println(("Shutting down stream"));
            streams.close();
        }));
    }
}
