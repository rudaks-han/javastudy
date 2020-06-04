import java.util.Arrays;
import java.util.List;

public class Test {

    List<Product> productList = Arrays.asList(
        new Product("computer"),
        new Product("rice")
    );

    public void test() {
        productList.stream().anyMatch(Product::isFood);
    }

}


