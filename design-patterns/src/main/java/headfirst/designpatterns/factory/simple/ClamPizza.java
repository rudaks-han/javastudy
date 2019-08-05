package headfirst.designpatterns.factory.simple;

public class ClamPizza extends Pizza {
    public ClamPizza() {
        name = "Clam Pizza";
        dough = "Thin Crust";
        sauce = "White garlic sauce";
        topping.add("clam");
        topping.add("Grated permesan cheese");
    }
}
