package kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("꽥~");
    }
}
