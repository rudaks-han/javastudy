package headfirst.designpatterns.decorator.pizza;

public class Olive extends ToppingDecorator {
    public Olive(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olive";
    }

    @Override
    public double cost() {
        return pizza.cost() + .30;
    }
}
