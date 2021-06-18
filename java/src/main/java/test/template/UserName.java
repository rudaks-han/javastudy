package test.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserName {
    //private String lastName;

    private String firstName;

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
