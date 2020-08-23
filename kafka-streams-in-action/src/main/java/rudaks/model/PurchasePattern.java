package rudaks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PurchasePattern {

    private double amount;

    public PurchasePattern(Purchase purchase) {
        this.amount = purchase.getPrice() * purchase.getQuantity();
    }
}
