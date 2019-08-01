package headfirst.designpatterns.decorator.starbuzz;

public class DarkRoast extends Beverage {
    public DarkRoast() {
        this.description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return 3.99;
    }
}
