package rudaks.chapter_4.transformer;

import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import rudaks.model.Purchase;
import rudaks.model.RewardAccumulator;

import java.util.Objects;

public class PurchaseRewardTransformer implements ValueTransformer<Purchase, RewardAccumulator> {

    private final String storeName;
    private KeyValueStore<String, Integer> stateStore;
    private ProcessorContext context;

    public PurchaseRewardTransformer(String storeName) {
        Objects.requireNonNull(storeName, "Store Name can't be null");
        this.storeName = storeName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void init(ProcessorContext context) {
        this.context = context;
        stateStore = (KeyValueStore) this.context.getStateStore(storeName);
    }

    @Override
    public RewardAccumulator transform(Purchase purchase) {
        RewardAccumulator rewardAccumulator = new RewardAccumulator(purchase);
        Integer accumulatedSoFar = stateStore.get(rewardAccumulator.getCustomerId());

        if (accumulatedSoFar != null) {
            rewardAccumulator.addRewardPoints(accumulatedSoFar);
        }

        /*
        System.err.println("customerId : " + rewardAccumulator.getCustomerId());
        System.err.println("accumulatedSoFar : " + accumulatedSoFar);
        System.err.println("getTotalRewardPoints() : " + rewardAccumulator.getTotalRewardPoints());
         */
        stateStore.put(rewardAccumulator.getCustomerId(), rewardAccumulator.getTotalRewardPoints());

        return rewardAccumulator;

    }

    @SuppressWarnings("deprecation")
    public RewardAccumulator punctuate(long timestamp) {
        return null;  //no-op null values not forwarded.
    }

    @Override
    public void close() {
        //no-op
    }
}
