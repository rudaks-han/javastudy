package etc.strategy.case1.step4.attack;

public class MissileStrategy implements AttackStrategy {
    @Override
    public void attack() {
        System.out.println("Missile 공격");
    }
}
