package etc.templatemethod.step1;

public class Test1 {
    public static void main(String[] args) {
        TagunV  tagunV = new TagunV();
        Transformer transformer = new Transformer();

        tagunV.fight();
        System.out.println("----------------------");
        transformer.fight();
    }
}
