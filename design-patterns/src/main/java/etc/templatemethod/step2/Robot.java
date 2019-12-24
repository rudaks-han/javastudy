package etc.templatemethod.step2;

public abstract class Robot {
    protected String name;

    public void fight() {
        System.out.println(name + "가 전투를 시작합니다.");
        attack();
        move();
        attack();
        System.out.println(name + "가 전투를 종료합니다.");
    }

    public abstract void attack();
    public abstract void move();
}
