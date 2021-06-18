package rudaks;

import java.time.Duration;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueStore;
import rudaks.model.UserDisconnect;
import rudaks.model.UserInfo;
import rudaks.temp.User;

public class TestProcessor extends AbstractProcessor<String, String> {
    private KeyValueStore<String, User> keyValueStore;

    private String stateStoreName;


    public TestProcessor(String stateStoreName) {
        this.stateStoreName = stateStoreName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext processorContext) {
        super.init(processorContext);
        keyValueStore = (KeyValueStore) context().getStateStore(stateStoreName);
        TestPunctuator punctuator = new TestPunctuator(context(), keyValueStore);

        context().schedule(Duration.ofSeconds(1), PunctuationType.WALL_CLOCK_TIME, punctuator);
    }

    @Override
    public void process(String sessionId, String str) {
        keyValueStore.put("test", new User(str));
    }
}
