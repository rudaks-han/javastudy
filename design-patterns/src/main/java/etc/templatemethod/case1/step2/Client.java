package etc.templatemethod.case1.step2;

public class Client {
    public static void main(String[] args) {
        CaffeineBeverage coffee = new Coffee();

        System.out.println("# 커피");
        coffee.prepare();

        CaffeineBeverage tea = new Tea();
        System.out.println("# Tea");
        tea.prepare();
    }
}
