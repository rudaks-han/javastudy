package etc.templatemethod.case1.step2;

public abstract class CaffeineBeverage {

    public final void prepare() {
        boilWater();
        brew();
        pourInCup();

        if (customerWantsCondiments())
            addCondiments();
    }

    protected void boilWater() {
        System.out.println("물끓이는 중");
    }

    protected abstract void brew();

    protected void pourInCup() {
        System.out.println("컵에 따르는 중");
    }

    protected abstract void addCondiments();

    protected boolean customerWantsCondiments() {
        return true;
    }
}
