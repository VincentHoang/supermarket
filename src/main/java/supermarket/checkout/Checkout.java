package supermarket.checkout;

import supermarket.checkout.calculation.TotalPriceCalculator;
import supermarket.checkout.util.ConvertBasketItemUtil;
import supermarket.data.StockInfo;

import java.util.List;

public class Checkout {

    private StockInfo stockInfo;
    private ConvertBasketItemUtil convertBasketItemUtil;
    private TotalPriceCalculator totalPriceCalculator;

    public Checkout(StockInfo stockInfo, ConvertBasketItemUtil convertBasketItemUtil, TotalPriceCalculator totalPriceCalculator) {
        this.stockInfo = stockInfo;
        this.convertBasketItemUtil = convertBasketItemUtil;
        this.totalPriceCalculator = totalPriceCalculator;
    }

    public double getPrice(List<String> itemIDs) {
        List<BasketItem> basketItems = convertBasketItemUtil.convertStringsToItems(itemIDs, stockInfo);
        return totalPriceCalculator.getTotalPrice(basketItems);
    }

}
