package example.processor;

import java.time.Duration;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueStore;

public class WaitCloseProcessor extends AbstractProcessor<String, String> {
    private KeyValueStore<String, Long> keyValueStore;

    private String stateStoreName;

    private int closeGap;

    public WaitCloseProcessor(String stateStoreName, int closeGap) {
        this.stateStoreName = stateStoreName;
        this.closeGap = closeGap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext processorContext) {
        super.init(processorContext);
        keyValueStore = (KeyValueStore) context().getStateStore(stateStoreName);
        ScheduledPunctuator punctuator = new ScheduledPunctuator(context(), keyValueStore, closeGap);

        //context().schedule(Duration.ofSeconds(1), PunctuationType.WALL_CLOCK_TIME, punctuator);
        context().schedule(10000, PunctuationType.WALL_CLOCK_TIME, punctuator);
    }

    @Override
    public void process(String userId, String message) {
        if (userId != null) {
            System.out.println(userId + ":" + message);
            keyValueStore.put(userId, System.currentTimeMillis());
        }
    }
}
