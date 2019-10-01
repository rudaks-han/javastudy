package blog.ifelse.type2_switch;

public class Calculator2 {

    public int calculateUsingSwitch(int a, int b, String operator) {
        int result = -1;
        switch (operator) {
            case "add":
                result = a + b;
                break;
            // other cases
        }
        return result;
    }

    public static void main(String[] args) {
        Calculator2 calculator = new Calculator2();
        int result = calculator.calculateUsingSwitch(1, 2, "add");

        System.out.println(result);
    }
}
