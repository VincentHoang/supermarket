package supermarket.checkout;

import java.util.List;

public class TotalPriceCalculator {
    private PriceCalculator priceCalculator;

    public TotalPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public double getTotalPrice(List<BasketItem> basketItems) {
        return basketItems.stream().mapToDouble(priceCalculator::getPrice).sum();
    }
}
