package headfirst.designpatterns.strategy.changebehavior;

import headfirst.designpatterns.strategy.changebehavior.model.Duck;
import headfirst.designpatterns.strategy.changebehavior.model.FlyRocketPowered;
import headfirst.designpatterns.strategy.changebehavior.model.MallardDuck;
import headfirst.designpatterns.strategy.changebehavior.model.ModelDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();

        mallard.performFly();
        mallard.performQuack();

        Duck model = new ModelDuck();
        model.performFly();

        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
