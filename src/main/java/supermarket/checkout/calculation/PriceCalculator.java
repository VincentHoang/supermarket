package supermarket.checkout.calculation;

import supermarket.checkout.BasketItem;
import supermarket.data.SpecialPrice;
import supermarket.data.StockKeepingUnit;

/**
 *  Calculate the price of one item including its quantity
 */
public class PriceCalculator {

    public double getPrice(BasketItem basketItem) {
        StockKeepingUnit stockKeepingUnit = basketItem.getStockKeepingUnit();

        int totalQuantity = basketItem.getQuantity();
        double unitPrice = stockKeepingUnit.getUnitPrice();

        return stockKeepingUnit.getSpecialPrice()
                .map(sp -> getPrice(sp, totalQuantity, unitPrice))
                .orElse(totalQuantity * unitPrice);
    }

    private Double getPrice(SpecialPrice specialPrice, int totalQuantity, double unitPrice) {
        if (totalQuantity < specialPrice.getQuantity()) {
            return totalQuantity * unitPrice;
        } else {
            return getWithSpecialPrice(specialPrice, totalQuantity, unitPrice);
        }
    }

    private double getWithSpecialPrice(SpecialPrice specialPrice, int totalQuantity, double unitPrice) {
        int currentQuantity = totalQuantity;
        int specialPriceQuantity = specialPrice.getQuantity();
        int totalPrice = 0;

        while(currentQuantity > 0) {
            if (currentQuantity >= specialPriceQuantity) {
                totalPrice += specialPrice.getPrice();
                currentQuantity -= specialPriceQuantity;
            } else {
                totalPrice += currentQuantity * unitPrice;
                currentQuantity = 0;
            }
        }
        return totalPrice;
    }
}
