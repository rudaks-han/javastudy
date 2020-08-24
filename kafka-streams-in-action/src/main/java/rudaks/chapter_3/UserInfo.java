package rudaks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String sessionId;
    private String userId;

    public String toString() {
        return JsonUtil.toJson(this);
    }
}
