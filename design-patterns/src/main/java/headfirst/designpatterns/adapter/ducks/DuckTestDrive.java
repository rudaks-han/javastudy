package headfirst.designpatterns.adapter.ducks;

public class DuckTestDrive {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();

        Turkey turkey = new WildTurkey();

        System.out.println("\nTurkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("\nDuck says...");
        duck.quack();
        duck.fly();

        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        turkeyAdapter.fly();
        turkeyAdapter.quack();

        System.out.println("\nTurkeyAdapter says");
        turkeyAdapter.fly();
        turkeyAdapter.quack();
    }
}
