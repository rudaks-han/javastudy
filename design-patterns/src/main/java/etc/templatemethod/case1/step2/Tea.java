package etc.templatemethod.case1.step2;

public class Tea extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("차를 우려내는 중");
    }

    @Override
    public void addCondiments() {
        System.out.println("레몬 넣는중");
    }
}
