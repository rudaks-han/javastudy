package kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior;

import kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior.model.Duck;
import kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior.model.FlyRocketPowered;
import kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior.model.MallardDuck;
import kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior.model.ModelDuck;

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
