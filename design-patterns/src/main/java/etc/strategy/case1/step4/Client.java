package etc.strategy.case1.step4;

import etc.strategy.case1.step4.attack.PunchStrategy;
import etc.strategy.case1.step4.move.FlyingStrategy;

public class Client {
    public static void main(String[] args) {
        TagunV tagunV = new TagunV("태권V");
        tagunV.setAttachStrategy(new PunchStrategy());
        tagunV.setMoveStrategy(new FlyingStrategy());

        tagunV.move();
        tagunV.attack();
    }
}
