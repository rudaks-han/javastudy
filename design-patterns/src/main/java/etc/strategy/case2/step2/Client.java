package etc.strategy.case2.step2;

import etc.strategy.case1.step4.move.FlyingStrategy;
import etc.strategy.case1.step4.move.WalkingStrategy;
import etc.strategy.case2.step2.cry.NoCryStategy;
import etc.strategy.case2.step2.cry.RoaringStrategy;

public class Client {
    public static void main(String[] args) {
        Animal tiger = new Tiger();
        tiger.setCryStrategy(new RoaringStrategy());
        tiger.setMoveStrategy(new WalkingStrategy());

        System.out.println("호랑이");
        tiger.move();
        tiger.cry();

        Animal eagle = new Eagle();
        eagle.setMoveStrategy(new FlyingStrategy());
        eagle.setCryStrategy(new NoCryStategy());

        System.out.println("\n독수리");
        eagle.move();
        eagle.cry();
    }
}
