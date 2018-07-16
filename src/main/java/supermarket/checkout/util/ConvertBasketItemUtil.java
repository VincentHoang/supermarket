package supermarket.checkout.util;

import supermarket.checkout.BasketItem;
import supermarket.data.StockInfo;
import supermarket.data.StockKeepingUnit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertBasketItemUtil {
    public List<BasketItem> convertStringsToItems(List<String> itemIDs, StockInfo stockInfo) {
        Map<String, StockKeepingUnit> stockKeepingUnits = stockInfo.getStockKeepingUnits();

        Map<String, List<String>> groupIDs = itemIDs.stream().collect(Collectors.groupingBy(id -> id));

        return groupIDs
                .entrySet()
                .stream()
                .filter(entry -> stockKeepingUnits.containsKey(entry.getKey()))
                .map(entry -> createItem(stockKeepingUnits, entry))
                .collect(Collectors.toList());
    }

    private BasketItem createItem(Map<String, StockKeepingUnit> stockKeepingUnits, Map.Entry<String, List<String>> entry) {
        return new BasketItem(stockKeepingUnits.get(entry.getKey()), entry.getValue().size());
    }
}
