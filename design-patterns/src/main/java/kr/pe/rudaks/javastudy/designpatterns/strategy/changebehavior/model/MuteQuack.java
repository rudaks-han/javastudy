package kr.pe.rudaks.javastudy.designpatterns.strategy.changebehavior.model;

public class MuteQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< 조용 >>");
    }
}
