package etc.templatemethod.step2;

public class TagunV extends Robot {

    public TagunV() {
        name = "트랜스포머";
    }
    @Override
    public void attack() {
        System.out.println("펀치 공격");
    }

    @Override
    public void move() {
        System.out.println("날아서 공격");
    }
}
