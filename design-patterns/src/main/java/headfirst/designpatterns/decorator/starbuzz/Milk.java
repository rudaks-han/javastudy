package headfirst.designpatterns.decorator.starbuzz;

public class Milk extends CondimentDecorator {
    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", add Milk";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
