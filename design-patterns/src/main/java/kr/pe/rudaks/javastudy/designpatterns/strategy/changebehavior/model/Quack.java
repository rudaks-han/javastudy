package kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior.model;

public class Quack implements QuackBehavior {
    public void quack() {
        System.out.println("꽥~");
    }
}
