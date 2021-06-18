package rudaks;

import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.apache.kafka.streams.state.KeyValueStore;
import rudaks.model.UserDisconnect;
import rudaks.temp.TestUser;
import rudaks.temp.User;

public class TestPunctuator implements Punctuator {

    private ProcessorContext context;

    private KeyValueStore<String, User> keyValueStore;

    private int logoutTimeoutMillis;

    public TestPunctuator(
        ProcessorContext context,
        KeyValueStore<String, User> keyValueStore
    ) {
        this.context = context;
        this.keyValueStore = keyValueStore;
    }

    @Override
    public void punctuate(long timestamp) {

        //context.forward("1", new User("rudaks " + timestamp));
        context.forward("1", new TestUser("rudaks " + timestamp));

    }
}
