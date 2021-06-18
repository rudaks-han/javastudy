package test.template;

public class TemplateTest {

    public static void main(String[] args) {
        //String json = "{\"id\": \"rudaks\", \"name\": \"한경만\"}";
        String json = "{\"id\": \"rudaks\", \"userName\": " +
                "{\"lastName\": \"한\", \"firstName\": \"경만\"}" +
                ", \"age\": 10}";
        //String json = "{\"id\": \"rudaks\"}";

        //System.out.println(json);
        User user = JsonUtil.fromJson(json, User.class);

        System.out.println(user.getUserName().getFirstName());
    }
}
