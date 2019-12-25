package etc.strategy.case2.step2;

import etc.strategy.case1.step4.move.MoveStrategy;
import etc.strategy.case2.step2.cry.CryStrategy;

public abstract class Animal {

    MoveStrategy moveStrategy;
    CryStrategy cryStrategy;


    public Animal() {

    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setCryStrategy(CryStrategy cryStrategy) {
        this.cryStrategy = cryStrategy;
    }

    public void cry() {
        this.cryStrategy.cry();
    }
    public void move() {
        this.moveStrategy.move();
    }
}
