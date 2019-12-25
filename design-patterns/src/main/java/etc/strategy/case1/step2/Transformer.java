package etc.strategy.case1.step2;

public class Transformer extends Robot {
    public Transformer() {
        name = "트랜스포머";
    }

    @Override
    public void attack() {
        System.out.println("미사일 공격");
    }

    @Override
    public void move() {
        System.out.println("걸어서 공격");
    }
}
