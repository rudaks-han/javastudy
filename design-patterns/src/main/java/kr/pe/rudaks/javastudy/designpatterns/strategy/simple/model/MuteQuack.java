package kr.pe.rudaks.javastudy.designpatterns.strategy.simple.model;

public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< 조용 >>");
    }
}
