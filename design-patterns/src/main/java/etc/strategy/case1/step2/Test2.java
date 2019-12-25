package etc.strategy.case1.step2;

public class Test2 {
    public static void main(String[] args) {
        TagunV  tagunV = new TagunV();
        Transformer transformer = new Transformer();

        tagunV.fight();
        System.out.println("----------------------");
        transformer.fight();
    }
}
