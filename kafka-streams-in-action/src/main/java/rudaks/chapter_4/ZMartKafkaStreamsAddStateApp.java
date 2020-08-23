/*
 * Copyright 2016 Bill Bejeck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rudaks.chapter_4;

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
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.kafka.support.serializer.JsonSerde;
import rudaks.chapter_4.partitioner.RewardsStreamPartitioner;
import rudaks.chapter_4.transformer.PurchaseRewardTransformer;
import rudaks.model.Purchase;
import rudaks.model.PurchasePattern;
import rudaks.model.RewardAccumulator;

import java.util.Properties;


public class ZMartKafkaStreamsAddStateApp {

    public static void main(String[] args) throws Exception {

        StreamsConfig streamsConfig = new StreamsConfig(getProperties());

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, Purchase> purchaseKStream = streamsBuilder.stream(
                "transactions",
                Consumed.with(Serdes.String(), new JsonSerde<>(Purchase.class))
        ).mapValues(p -> p.maskCreditCard());

        KStream<String, PurchasePattern> patternKStream = purchaseKStream.mapValues(
                purchase -> new PurchasePattern(purchase)
        );

        patternKStream.print(Printed.<String, PurchasePattern>toSysOut().withLabel("patterns"));
        patternKStream.to("patterns", Produced.with(Serdes.String(), new JsonSerde<>(PurchasePattern.class)));


        // adding State to processor
        String rewardsStateStoreName = "rewardsPointsStore";
        RewardsStreamPartitioner streamPartitioner = new RewardsStreamPartitioner();

        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(rewardsStateStoreName);
        StoreBuilder<KeyValueStore<String, Integer>> storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, Serdes.String(), Serdes.Integer());

        streamsBuilder.addStateStore(storeBuilder);

        KStream<String, Purchase> transByCustomerStream = purchaseKStream.through(
                "customer_transactions",
                Produced.with(Serdes.String(), new JsonSerde<>(Purchase.class), streamPartitioner
                )
        );


        KStream<String, RewardAccumulator> statefulRewardAccumulator = transByCustomerStream.transformValues(() -> new PurchaseRewardTransformer(rewardsStateStoreName),
                rewardsStateStoreName);

        //statefulRewardAccumulator.print(Printed.<String, RewardAccumulator>toSysOut().withLabel("rewards"));
        statefulRewardAccumulator.to("rewards", Produced.with(Serdes.String(), new JsonSerde<>(RewardAccumulator.class)));

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamsConfig);
        kafkaStreams.cleanUp();
        kafkaStreams.start();

    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.CLIENT_ID_CONFIG, "AddingStateConsumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "AddingStateGroupId");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "AddingStateAppId");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        return props;
    }

}
