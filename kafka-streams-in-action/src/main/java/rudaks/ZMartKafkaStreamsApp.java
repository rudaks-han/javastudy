package rudaks;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.kafka.support.serializer.JsonSerde;
import rudaks.model.Purchase;
import rudaks.model.PurchasePattern;
import rudaks.model.RewardAccumulator;

import java.util.Properties;

public class ZMartKafkaStreamsApp {

    public static void main(String[] args) {
        StreamsConfig streamsConfig = new StreamsConfig(getProperties());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        /*KStream<String,Purchase> purchaseKStream = streamsBuilder.stream(
                "transactions",
                Consumed.with(Serdes.String(), new JsonSerde<>(Purchase.class))
        ).mapValues(p -> Purchase.builder(p).maskCreditCard().build());

        KStream<String, PurchasePattern> patternKStream = purchaseKStream.mapValues(
                purchase -> PurchasePattern.builder(purchase).build()
        );
        */

        KStream<String, Purchase> purchaseKStream = streamsBuilder.stream(
                "transactions",
                Consumed.with(Serdes.String(), new JsonSerde<>(Purchase.class))
        ).mapValues(p -> p.maskCreditCard());


        KStream<String, PurchasePattern> patternKStream = purchaseKStream.mapValues(
                purchase -> new PurchasePattern(purchase)
        );

        patternKStream.print(Printed.<String, PurchasePattern>toSysOut().withLabel("patterns"));
        patternKStream.to("patterns", Produced.with(Serdes.String(), new JsonSerde<>(PurchasePattern.class)));

        //KStream<String, RewardAccumulator> rewardsKStream = purchaseKStream.mapValues(purchase -> RewardAccumulator.builder(purchase).build());
        KStream<String, RewardAccumulator> rewardsKStream = purchaseKStream.mapValues(purchase -> new RewardAccumulator(purchase));

        rewardsKStream.print(Printed.<String, RewardAccumulator>toSysOut().withLabel("rewards"));
        rewardsKStream.to("rewards", Produced.with(Serdes.String(), new JsonSerde<>(RewardAccumulator.class)));

        /*
        purchaseKStream.print(Printed.<String, Purchase>toSysOut().withLabel("purchases"));
        purchaseKStream.to("purchases", Produced.with(stringSerde,purchaseSerde));


        // used only to produce data for this application, not typical usage
        MockDataProducer.producePurchaseData();

*/

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamsConfig);
        System.out.println("ZMart First Kafka Streams Application Started");
        kafkaStreams.start();

    }

    private static Properties getProperties() {
        // 아래 설정 확인 필요
        // CLIENT_ID_CONFIG, GROUP_ID_CONFIG, DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG
        Properties props = new Properties();
        props.put(StreamsConfig.CLIENT_ID_CONFIG, "FirstZmart-Kafka-Streams-Client");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "zmart-purchases");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "FirstZmart-Kafka-Streams-App");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        return props;
    }
}
