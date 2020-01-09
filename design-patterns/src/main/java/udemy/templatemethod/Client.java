package udemy.templatemethod;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        Order order = new Order("1001");

        order.addItem("Soda", 2.50);
        order.addItem("Sandwich", 11.95);
        order.addItem("Pizza", 15.95);

        //OrderPrinter printer = new TextPrinter();
        //printer.printOrder(order, "1001.txt");

        OrderPrinter printer = new HtmlPrinter();
        printer.printOrder(order, "1001.html");

    }
}
