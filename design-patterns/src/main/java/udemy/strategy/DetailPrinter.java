package udemy.strategy;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class DetailPrinter implements OrderPrinter {

    @Override
    public void print(Collection<Order> orders) {
        System.out.println("*********** Detail Report **********");
        Iterator<Order> iterator = orders.iterator();
        double total = 0;
        while(iterator.hasNext()) {
            double orderTotal = 0;
            Order order = iterator.next();
            System.out.println(order.getId() + "\t" + order.getDate());

            for (Map.Entry<String, Double> entry  : order.getItems().entrySet()) {
                System.out.println("\t\t" + entry.getKey() + "\t" + entry.getValue());
                orderTotal += entry.getValue();
            }

            System.out.println("-------------------------------------");
            System.out.println("\t\t Total " + orderTotal);
            System.out.println("-------------------------------------");

            total += order.getTotal();
        }

        System.out.println("*****************************");
        System.out.println("\t\t\t Total: " + total);
    }
}
