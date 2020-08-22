package rudaks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rudaks.JsonUtil;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class Purchase {

    private static final String CC_NUMBER_REPLACEMENT = "xxxx-xxxx-xxxx-";
    private String customerId;
    private String creditCardNumber;
    private int quantity;
    private double price;

    public Purchase(String customerId, String creditCardNumber, int quantity, double price) {
        this.customerId = customerId;
        this.creditCardNumber = creditCardNumber;
        this.quantity = quantity;
        this.price = price;
    }

    public static void main(String[] args) {
        Purchase purchase = new Purchase("rudaks", "1234-1234-1234-1234", 1000, 1);

        System.out.println(purchase);
    }

    public Purchase maskCreditCard() {
        Objects.requireNonNull(this.creditCardNumber, "Credit Card can't be null");
        String[] parts = this.creditCardNumber.split("-");
        if (parts.length < 4) {
            this.creditCardNumber = "xxxx";
        } else {
            String last4Digits = this.creditCardNumber.split("-")[3];
            this.creditCardNumber = CC_NUMBER_REPLACEMENT + last4Digits;
        }
        return this;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}