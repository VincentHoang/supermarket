import supermarket.data.StockInfoFactory;
import supermarket.checkout.Checkout;
import supermarket.checkout.ConvertBasketItemUtil;
import supermarket.checkout.PriceCalculator;
import supermarket.checkout.TotalPriceCalculator;
import supermarket.data.StockInfo;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        StockInfo stockInfo = new StockInfoFactory().create(
                "A,50,3,130\n" +
                        "B,30,2,45\n" +
                        "C,20,5,80\n" +
                        "D,15"
        );

        Checkout checkout = new Checkout(
                stockInfo,
                new ConvertBasketItemUtil(),
                new TotalPriceCalculator(new PriceCalculator()));

        System.out.println(checkout.getPrice(Arrays.asList("A", "B", "A", "B", "C", "C", "C", "C", "C")));
    }
}
