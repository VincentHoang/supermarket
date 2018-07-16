package supermarket;

import org.junit.Before;
import org.junit.Test;
import supermarket.data.SpecialPrice;
import supermarket.data.StockInfo;
import supermarket.data.StockKeepingUnit;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StockInfoFactoryTest {

    StockInfoFactory stockInfoFactory;

    @Before
    public void setup() {
        stockInfoFactory = new StockInfoFactory();
    }

    @Test
    public void given_a_csv_string_then_create_stockInfo_with_StockKeepingUnits() {
        StockInfo stockInfo = stockInfoFactory.create(
                "A,50,3,130\n" +
                        "B,30,2,45\n" +
                        "C,20\n"
        );

        Map<String, StockKeepingUnit> stockKeepingUnits = stockInfo.getStockKeepingUnits();
        assertThat(stockKeepingUnits.get("A"), is(new StockKeepingUnit("A", 50, new SpecialPrice(3, 130))));
        assertThat(stockKeepingUnits.get("B"), is(new StockKeepingUnit("B", 30, new SpecialPrice(2, 45))));
        assertThat(stockKeepingUnits.get("C"), is(new StockKeepingUnit("C", 20)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_csv_string_with_negative_quantity_then_throw_IllegalArgumentException() {
        stockInfoFactory.create(
                "A,50,3,130\n" +
                        "B,30,-2,45\n" +
                        "C,20\n"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_line_of_csv_string_insufficient_then_throw_IllegalArgumentException() {
        stockInfoFactory.create(
                "A,\n" +
                        "B,30,-2,45\n" +
                        "C,20\n"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_csv_string_with_incorrect_types_then_throw_IllegalArgumentException() {
        stockInfoFactory.create(
                "A,FOO\n" +
                        "B,30,-2,45\n" +
                        "C,20\n"
        );
    }
}