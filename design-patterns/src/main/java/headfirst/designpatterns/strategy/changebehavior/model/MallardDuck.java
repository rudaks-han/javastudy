package headfirst.designpatterns.strategy.changebehavior.model;

public class MallardDuck extends Duck {
    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    public void display() {
        System.out.println("저는 물오리입니다~");
    }
}
