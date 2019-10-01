package blog.ifelse.type1;

public class Calculator {

    public int calculate(int a, int b, String operator) {
        int result = Integer.MIN_VALUE;

        if ("add".equals(operator)) {
            result = a + b;
        } else if ("multiply".equals(operator)) {
            result = a * b;
        } else if ("divide".equals(operator)) {
            result = a / b;
        } else if ("subtract".equals(operator)) {
            result = a - b;
        }
        return result;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(1, 2, "add");

        System.out.println(result);
    }
}
