package blog.ifelse.type5_command;

public class Calculator {

    public int calculate(Command command) {
        return command.execute();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int value = calculator.calculate(new AddCommand(1, 2));

        System.out.println(value);
    }
}
