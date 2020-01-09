package udemy.strategy;

import java.util.LinkedList;

public class Client {
    private static LinkedList<Order> orders = new LinkedList<>();

    public static void main(String[] args) {
        createOrders();

        PrintService service = new PrintService(new SummaryPrinter());
        service.printOrders(orders);

        service = new PrintService(new DetailPrinter());
        service.printOrders(orders);

    }

    private static void createOrders() {
        Order order = new Order("100");
        order.addItem("Soda", 2);
        order.addItem("Chips", 10);
        orders.add(order);

        order = new Order("200");
        order.addItem("Cake", 20);
        order.addItem("Cookies", 5);
        orders.add(order);

        order = new Order("300");
        order.addItem("Burger", 8);
        order.addItem("Fries", 5);
        orders.add(order);
    }
}
