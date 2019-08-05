package headfirst.designpatterns.factory.pizza;

public class ChicagoStyleCheesePizza extends Pizza {

    public ChicagoStyleCheesePizza() {
        name = "Chicago style deep dish cheese pizza";
        dough = "Extra thick crust dough";
        sauce = "Plum tomato sauce";

        topping.add("Shredded mozzarella cheese");
    }

    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
