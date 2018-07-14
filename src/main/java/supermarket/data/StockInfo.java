package supermarket.data;

import java.util.Map;

public class StockInfo {
    private Map<String, StockKeepingUnit> stockKeepingUnits;

    public StockInfo(Map<String, StockKeepingUnit> stockKeepingUnits) {
        this.stockKeepingUnits = stockKeepingUnits;
    }

    public Map<String, StockKeepingUnit> getStockKeepingUnits() {
        return stockKeepingUnits;
    }
}
