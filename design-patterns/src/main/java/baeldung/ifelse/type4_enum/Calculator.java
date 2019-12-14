package baeldung.ifelse.type4_enum;

public class Calculator {

    public int calculate(int a, int b, Operator operator) {
        return operator.apply(a, b);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int value = calculator.calculate(1,2, Operator.ADD);

        System.out.println(value);
    }
}
