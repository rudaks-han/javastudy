package rudaks;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.kafka.support.serializer.JsonSerde;
import rudaks.processor.LogoutTimeoutProcessor;
import rudaks.temp.User;

public class HelloStreams {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "app-id");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Topology topology = new Topology();
        String store = "testStore";

        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(store);
        StoreBuilder<KeyValueStore<String, User>> storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, Serdes.String(), new JsonSerde<>(User.class));

        TestProcessor testProcessor = new TestProcessor(store);

        topology
            .addSource("source", Serdes.String().deserializer(), Serdes.String().deserializer(), "streams-test-in")
            .addProcessor("processor", () -> testProcessor, "source")
            .addStateStore(storeBuilder, "processor")
            .addSink("sink", "streams-test-out", Serdes.String().serializer(), new JsonSerde<>(User.class).serializer(), "processor");


        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            streams.close();
        }));
    }
}
