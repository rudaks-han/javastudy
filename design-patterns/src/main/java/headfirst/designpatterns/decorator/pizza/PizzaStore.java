package headfirst.designpatterns.decorator.pizza;

public class PizzaStore {
    public static void main(String[] args) {
        Pizza pizza = new ThincrustPizza();
        Pizza cheesePizza = new Cheese(pizza);
        Pizza greekPizza = new Olive(pizza);

        System.out.println(greekPizza.getDescription() + " $" + greekPizza.cost());

    }
}
