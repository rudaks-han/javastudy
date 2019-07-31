package kr.pe.rudaks.javastudy.designpatterns.strategy.simple;

import kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model.Duck;
import kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model.MallardDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();

        mallard.performFly();
        mallard.performQuack();
        mallard.swim();
    }
}
