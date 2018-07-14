package supermarket.data;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * convert csv string into POJO
 * This class depends on how we want to "pass in a set of pricing rules"
 * Therefore, I didn't spend much time on error handling
 */
public class StockInfoFactory {
    public StockInfo create(String rules) {
        String[] stockKeepingUnitStrings = rules.split("\n");

        Map<String, StockKeepingUnit> stockKeepingUnits = Arrays.stream(stockKeepingUnitStrings).map(line -> {
            String[] split = line.split(",");

            if (split.length == 4) {
                return createStockKeepingUnitWithSpecialPrice(split);
            } else if (split.length == 2) {
                return createStockKeepingUnitWithoutSpecialPrice(split);
            } else {
                throw new IllegalArgumentException(rules);
            }

        }).collect(Collectors.toMap(StockKeepingUnit::getItemID, u -> u));

        return new StockInfo(stockKeepingUnits);
    }

    private StockKeepingUnit createStockKeepingUnitWithoutSpecialPrice(String[] skuString) {
        String itemID = skuString[0];
        double unitPrice = Double.parseDouble(skuString[1]);
        return new StockKeepingUnit(itemID, unitPrice);
    }

    private StockKeepingUnit createStockKeepingUnitWithSpecialPrice(String[] skuString) {
        String itemID = skuString[0];
        double unitPrice = Double.parseDouble(skuString[1]);
        int quantity = Integer.parseInt(skuString[2]);
        double price = Double.parseDouble(skuString[3]);
        return new StockKeepingUnit(itemID, unitPrice, new SpecialPrice(quantity, price));
    }
}
