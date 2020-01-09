package udemy.strategy;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Order {
    private String id;
    private LocalDate date;
    private Map<String, Double> items = new HashMap<>();
    private double total;

    public Order(String id) {
        this.id = id;
        date = LocalDate.now();
    }

    public void addItem(String id, double value) {
        this.items.put(id, value);
    }
}
