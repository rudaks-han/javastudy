package headfirst.designpatterns.strategy.changebehavior.model;

public class FlyWithWings implements FlyBehavior {
    public void fly() {
        System.out.println("날고 있어요~");
    }
}
