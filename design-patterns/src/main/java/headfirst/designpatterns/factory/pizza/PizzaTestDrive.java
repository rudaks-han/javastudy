package headfirst.designpatterns.factory.pizza;

public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        chicagoPizzaStore.orderPizza("cheese");

        PizzaStore nyPizzaStore = new NYStylePizzaStore();
        nyPizzaStore.orderPizza("cheese");
    }
}
