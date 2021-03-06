package rudaks.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;
import rudaks.model.BeerPurchase;
import rudaks.model.Currency;

import java.text.DecimalFormat;

import static rudaks.model.Currency.DOLLARS;

public class BeerPurchaseProcessor extends AbstractProcessor<String, BeerPurchase> {

    private String domesticSalesNode;
    private String internationalSalesNode;

    public BeerPurchaseProcessor(String domesticSalesNode, String internationalSalesNode) {
        this.domesticSalesNode = domesticSalesNode;
        this.internationalSalesNode = internationalSalesNode;
    }

    @Override
    public void process(String key, BeerPurchase beerPurchase) {

        Currency transactionCurrency = beerPurchase.getCurrency();
        if (transactionCurrency != DOLLARS) {
            BeerPurchase dollarBeerPurchase;
            BeerPurchase.Builder builder = BeerPurchase.newBuilder(beerPurchase);
            double internationalSaleAmount = beerPurchase.getTotalSale();
            String pattern = "###.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            builder.currency(DOLLARS);
            builder.totalSale(Double.parseDouble(decimalFormat.format(transactionCurrency.convertToDollars(internationalSaleAmount))));
            dollarBeerPurchase = builder.build();
            context().forward(key, dollarBeerPurchase, internationalSalesNode);
        } else {
            context().forward(key, beerPurchase, domesticSalesNode);
        }

    }
}
