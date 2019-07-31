package kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model;

public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("날고 있어요~");
    }
}
