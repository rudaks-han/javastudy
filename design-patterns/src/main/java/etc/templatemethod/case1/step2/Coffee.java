package etc.templatemethod.case1.step2;

public class Coffee extends CaffeineBeverage {

    @Override
    public void brew() {
        System.out.println("커피 우려내는 중");
    }

    @Override
    public void addCondiments() {
        System.out.println("설탕과 우유 넣는중");
    }
}
