package supermarket.checkout;

import supermarket.data.StockInfo;
import supermarket.data.StockKeepingUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(MockitoJUnitRunner.class)
public class ConvertBasketItemUtilTest {

    private ConvertBasketItemUtil util;

    private StockInfo stockInfo;

    private String itemID1 = "itemA";
    private String itemID2 = "itemB";
    private String itemID3 = "itemC";

    @Mock private StockKeepingUnit unit1;
    @Mock private StockKeepingUnit unit2;


    @Before
    public void setup() {
        util = new ConvertBasketItemUtil();

        Map<String, StockKeepingUnit> stringStockKeepingUnits = new HashMap<>();
        stringStockKeepingUnits.put("itemA", unit1);
        stringStockKeepingUnits.put("itemB", unit2);

        stockInfo = new StockInfo(stringStockKeepingUnits);
    }

    @Test
    public void convert_list_of_strings_to_list_of_items() {
        List<String> itemIDs = Arrays.asList(itemID1, itemID2);

        List<BasketItem> basketItems = util.convertToItem(itemIDs, stockInfo);

        assertThat(basketItems, containsInAnyOrder(new BasketItem(unit1, 1), new BasketItem(unit2, 1)));
    }

    @Test
    public void given_a_list_of_duplicate_strings_then_do_not_duplicate_item() {
        List<String> itemIDs = Arrays.asList(itemID1, itemID2, itemID1);

        List<BasketItem> basketItems = util.convertToItem(itemIDs, stockInfo);

        assertThat(basketItems, containsInAnyOrder(new BasketItem(unit1, 2), new BasketItem(unit2, 1)));
    }

    @Test
    public void given_a_list_of_strings_that_are_not_in_stockInfo_then_exclude_them() {
        List<String> itemIDs = Arrays.asList(itemID1, itemID3);

        List<BasketItem> basketItems = util.convertToItem(itemIDs, stockInfo);

        assertThat(basketItems, containsInAnyOrder(new BasketItem(unit1, 1)));
    }
}