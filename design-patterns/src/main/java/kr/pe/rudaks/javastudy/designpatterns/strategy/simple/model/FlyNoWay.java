package kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model;

public class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println("저는 못 날아요~");
    }
}
