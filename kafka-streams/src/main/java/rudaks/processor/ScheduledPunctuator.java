package rudaks.processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import rudaks.model.UserDisconnect;

public class ScheduledPunctuator implements Punctuator {

    private ProcessorContext context;

    private KeyValueStore<String, UserDisconnect> keyValueStore;

    private int logoutTimeoutMillis;

    public ScheduledPunctuator(
        ProcessorContext context,
        KeyValueStore<String, UserDisconnect> keyValueStore,
        int logoutTimeoutMillis
    ) {
        this.context = context;
        this.keyValueStore = keyValueStore;
        this.logoutTimeoutMillis = logoutTimeoutMillis;
    }

    @Override
    public void punctuate(long timestamp) {
        KeyValueIterator<String, UserDisconnect> performanceIterator = keyValueStore.all();
        while (performanceIterator.hasNext()) {
            KeyValue<String, UserDisconnect> keyValue = performanceIterator.next();
            String key = keyValue.key;
            UserDisconnect value = keyValue.value;

            long disonnectedAt = keyValue.value.getDisconnectedAt();

            if (disonnectedAt > 0) {
                if (timestamp - disonnectedAt > logoutTimeoutMillis) {
                    System.out.println("logout으로 처리........." + value.getUserId());
                    keyValueStore.delete(key);

                    context.forward(key, value);
                }
            }
        }

        performanceIterator.close();
    }
}
