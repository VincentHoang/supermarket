package supermarket.checkout;


import supermarket.data.SpecialPrice;
import supermarket.data.StockInfo;
import supermarket.data.StockKeepingUnit;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutComponentTest {

    private Checkout checkout;

    @Before
    public void setUp() {
        HashMap<String, StockKeepingUnit> units = new HashMap<>();
        units.put("A", new StockKeepingUnit("A", 50, new SpecialPrice(3, 15)));
        units.put("B", new StockKeepingUnit("B", 30, new SpecialPrice(2, 45)));
        units.put("C", new StockKeepingUnit("C", 20));
        units.put("D", new StockKeepingUnit("D", 15));

        checkout = new Checkout(
                new StockInfo(units),
                new ConvertBasketItemUtil(),
                new TotalPriceCalculator(new PriceCalculator()));
    }

    @Test
    public void given_some_items_then_return_the_correct_price() {
        List<String> items = Arrays.asList("A", "B", "C", "D");
        double price = this.checkout.getPrice(items);

        assertThat(price, is(115.0));
    }

    @Test
    public void given_some_items_with_special_prices_then_return_the_correct_price() {
        List<String> items = Arrays.asList("B", "A", "B");
        double price = this.checkout.getPrice(items);

        assertThat(price, is(95.0));
    }
}