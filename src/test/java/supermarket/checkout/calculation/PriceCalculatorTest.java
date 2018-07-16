package supermarket.checkout.calculation;


import supermarket.checkout.BasketItem;
import supermarket.checkout.calculation.PriceCalculator;
import supermarket.data.SpecialPrice;
import supermarket.data.StockKeepingUnit;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class PriceCalculatorTest {

    private PriceCalculator priceCalculator;

    @Before
    public void setup() {
        priceCalculator = new PriceCalculator();
    }

    @Test
    public void given_quantity_is_less_than_the_special_price_quantity_then_getPrice_returns_the_regular_price() {
        SpecialPrice specialPrice = new SpecialPrice(3, 130);
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit("A", 50, specialPrice);
        BasketItem basketItem = new BasketItem(stockKeepingUnit, 1);

        double price = priceCalculator.getPrice(basketItem);

        assertThat(price, is(50.0));
    }

    @Test
    public void given_special_price_quantity_is_1_then_getPrice_returns_special_price() {
      SpecialPrice specialPrice = new SpecialPrice(1, 40);
      StockKeepingUnit stockKeepingUnit = new StockKeepingUnit("A", 50, specialPrice);
      BasketItem basketItem = new BasketItem(stockKeepingUnit, 3);

      double price = priceCalculator.getPrice(basketItem);

      assertThat(price, is(120.0));
    }

    @Test
    public void given_quantity_equals_special_price_quantity_then_getPrice_returns_special_price() {
        SpecialPrice specialPrice = new SpecialPrice(3, 130);
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit("A", 50, specialPrice);
        BasketItem basketItem = new BasketItem(stockKeepingUnit, 3);

        double price = priceCalculator.getPrice(basketItem);

        assertThat(price, is(130.0));
    }

    @Test
    public void given_quantity_is_a_factor_of_special_price_quantity_then_getPrice_returns_special_price_multiplied() {
        SpecialPrice specialPrice = new SpecialPrice(3, 130);
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit("A", 50, specialPrice);
        BasketItem basketItem = new BasketItem(stockKeepingUnit, 6);

        double price = priceCalculator.getPrice(basketItem);

        assertThat(price, is(260.0));
    }

    @Test
    public void given_quantity_is_greater_than_special_price_quantity_then_getPrice_returns_correct_price() {
        SpecialPrice specialPrice = new SpecialPrice(3, 130);
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit("A", 50, specialPrice);
        BasketItem basketItem = new BasketItem(stockKeepingUnit, 5);

        double price = priceCalculator.getPrice(basketItem);

        assertThat(price, is(230.0));
    }

    @Test
    public void given_no_special_price_then_then_getPrice_returns_normal_price() {
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit("D", 15);
        BasketItem basketItem = new BasketItem(stockKeepingUnit, 5);

        double price = priceCalculator.getPrice(basketItem);

        assertThat(price, is(75.0));
    }

}