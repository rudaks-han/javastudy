package headfirst.designpatterns.factory.simple;

public class PizzaTestDrive {
    public static void main(String[] args) {
        SimplePizzaFactory factory = new SimplePizzaFactory();

        PizzaStore pizzaStore = new PizzaStore(factory);
        pizzaStore.orderPizza("clam");

        pizzaStore.orderPizza("cheese");
    }
}
