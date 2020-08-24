package example.processor;

import java.util.Date;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

public class ScheduledPunctuator implements Punctuator {

    private ProcessorContext context;

    private KeyValueStore<String, Long> keyValueStore;

    private int closeGap;

    public ScheduledPunctuator(
        ProcessorContext context,
        KeyValueStore<String, Long> keyValueStore,
        int closeGap
    ) {

        this.context = context;
        this.keyValueStore = keyValueStore;
        this.closeGap = closeGap;
    }

    @Override
    public void punctuate(long timestamp) {
        KeyValueIterator<String, Long> performanceIterator = keyValueStore.all();

        while (performanceIterator.hasNext()) {
            KeyValue<String, Long> keyValue = performanceIterator.next();
            String key = keyValue.key;
            Long lastUpdatedTimestamp = keyValue.value;
            if (timestamp - lastUpdatedTimestamp > closeGap) {
                System.out.println("[CLOSE REQUESTS]:" + key + "[" + new Date() + "]");
                keyValueStore.delete(key);
            }
        }
    }
}
