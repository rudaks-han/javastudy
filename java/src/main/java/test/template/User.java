package test.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    //private String id;

    private UserName userName;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
