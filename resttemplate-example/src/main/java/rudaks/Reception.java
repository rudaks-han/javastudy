package rudaks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Reception {
    private String appChannelId;

    private String topicId;

    private String firstMessage;

    public static Reception sample() {
        return new Reception("mobile", "topic1", "test1");
    }
}
