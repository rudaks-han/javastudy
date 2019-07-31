package kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model;

public class Squeak implements QuackBehavior {
    public void quack() {
        System.out.println("삑~");
    }
}
