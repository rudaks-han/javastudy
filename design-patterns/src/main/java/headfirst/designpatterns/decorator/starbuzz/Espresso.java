package headfirst.designpatterns.decorator.starbuzz;

public class Espresso extends Beverage {
    public Espresso() {
        this.description = "Espresso Coffee";
    }

    @Override
    public double cost() {
        return 2.99;
    }
}
