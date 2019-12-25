package etc.strategy.case1.step4;

import etc.strategy.case1.step4.attack.AttackStrategy;
import etc.strategy.case1.step4.move.MoveStrategy;

public abstract class Robot {
    private String name;
    private AttackStrategy attackStrategy;
    private MoveStrategy moveStrategy;

    public Robot(String name) {
        this.name = name;
    }

    public void move() {
        moveStrategy.move();
    }

    public void attack() {
        attackStrategy.attack();
    }

    public void setAttachStrategy(AttackStrategy attachStrategy) {
        this.attackStrategy = attachStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }
}
