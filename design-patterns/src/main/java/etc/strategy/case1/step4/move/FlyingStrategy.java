package etc.strategy.case1.step4.move;

public class FlyingStrategy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("날아서 이동");
    }
}
