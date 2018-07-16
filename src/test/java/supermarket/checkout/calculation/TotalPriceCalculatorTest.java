package supermarket.checkout.calculation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import supermarket.checkout.BasketItem;
import supermarket.checkout.calculation.PriceCalculator;
import supermarket.checkout.calculation.TotalPriceCalculator;

@RunWith(MockitoJUnitRunner.class)
public class TotalPriceCalculatorTest {

    private TotalPriceCalculator totalPriceCalculator;
    @Mock private PriceCalculator priceCalculator;

    @Mock
    BasketItem basketItem1;
    @Mock
    BasketItem basketItem2;
    double price1 = 10.0;
    double price2 = 30.0;

    @Before
    public void setup() {
        totalPriceCalculator = new TotalPriceCalculator(priceCalculator);
    }

    @Test
    public void getPrice_returns_total_of_price_calculators() {
        when(priceCalculator.getPrice(basketItem1)).thenReturn(price1);
        when(priceCalculator.getPrice(basketItem2)).thenReturn(price2);

        List<BasketItem> basketItems = Arrays.asList(basketItem1, basketItem2);
        double totalPrice = totalPriceCalculator.getTotalPrice(basketItems);
        assertThat(totalPrice, is(40.0));
    }
}