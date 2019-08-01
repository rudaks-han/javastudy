package headfirst.designpatterns.strategy.changebehavior.model;

public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("삑~");
    }
}
