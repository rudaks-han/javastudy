package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class MyJackson {

    private String value;
    private int count;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // 객체에 @Getter가 없으면 아래 라인을 추가해야 한다.
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new MyJackson());

        System.out.println(result);
    }
}
