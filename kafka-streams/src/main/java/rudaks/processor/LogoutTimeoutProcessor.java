package rudaks.processor;

import java.time.Duration;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueStore;
import rudaks.model.UserDisconnect;
import rudaks.model.UserInfo;

public class LogoutTimeoutProcessor extends AbstractProcessor<String, UserInfo> {
    private KeyValueStore<String, UserDisconnect> keyValueStore;

    private String stateStoreName;

    private int logoutTimeoutMillis;

    public LogoutTimeoutProcessor(String stateStoreName, int logoutTimeoutMillis) {
        this.stateStoreName = stateStoreName;
        this.logoutTimeoutMillis = logoutTimeoutMillis;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext processorContext) {
        super.init(processorContext);
        keyValueStore = (KeyValueStore) context().getStateStore(stateStoreName);
        ScheduledPunctuator punctuator = new ScheduledPunctuator(context(), keyValueStore, logoutTimeoutMillis);

        context().schedule(Duration.ofSeconds(1), PunctuationType.WALL_CLOCK_TIME, punctuator);
    }

    @Override
    public void process(String sessionId, UserInfo userInfo) {
        if (userInfo != null) {
            if (userInfo.getUserId() != null) { // connect
                keyValueStore.put(userInfo.getSessionId(), new UserDisconnect(userInfo.getSessionId(), userInfo.getUserId(), -1));
            } else { // disconnect
                UserDisconnect userDisconnect = keyValueStore.get(userInfo.getSessionId());
                keyValueStore.put(userInfo.getSessionId(), new UserDisconnect(userInfo.getSessionId(), userDisconnect.getUserId(), System.currentTimeMillis()));
            }
        }
    }
}
