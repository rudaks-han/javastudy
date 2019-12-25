package etc.strategy.case2.step2.cry;

public class NoCryStategy implements CryStrategy {
    @Override
    public void cry() {
        System.out.println("울지 않음");
    }
}
