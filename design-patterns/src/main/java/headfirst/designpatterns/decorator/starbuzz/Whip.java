package headfirst.designpatterns.decorator.starbuzz;

public class Whip extends CondimentDecorator {
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.description + ", add Whip";
    }

    @Override
    public double cost() {
        return 0.55;
    }
}
