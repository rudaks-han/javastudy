package headfirst.designpatterns.decorator.starbuzz;

public class StarBuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        Beverage beverageWithWhip = new Milk(beverage);

        System.out.println(beverageWithWhip.getDescription() + " $" + beverageWithWhip.cost());
    }
}
