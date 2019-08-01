package headfirst.designpatterns.strategy.simple;

import headfirst.designpatterns.strategy.simple.model.Duck;
import headfirst.designpatterns.strategy.simple.model.MallardDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();

        mallard.performFly();
        mallard.performQuack();
        mallard.swim();
    }
}
