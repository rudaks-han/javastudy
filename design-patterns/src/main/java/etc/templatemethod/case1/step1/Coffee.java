package etc.templatemethod.case1.step1;

public class Coffee {

    void prepare() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }

    public void boilWater() {
        System.out.println("물끓이는 중");
    }

    public void brewCoffeeGrinds() {
        System.out.println("커피 우려내는 중");
    }

    public void pourInCup() {
        System.out.println("컵에 따르는 중");
    }

    public void addSugarAndMilk() {
        System.out.println("설탕과 우유 넣는중");
    }
}
