package blog.ifelse.type3_factory;

public class Calculator {

    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int value = calculator.calculateUsingFactory(1, 2, "add");

        System.out.println(value);
    }
}
