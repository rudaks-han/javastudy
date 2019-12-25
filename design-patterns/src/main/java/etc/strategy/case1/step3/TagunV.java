package etc.strategy.case1.step3;

public class TagunV extends Robot {

    private Attackable attackable = new MissileAttach();
    private Movable movable = new FlyingMove();

    public TagunV() {
        name = "태권브이";
    }

    @Override
    public void attack() {
        attackable.attack();
    }

    @Override
    public void move() {
        movable.move();
    }
}
