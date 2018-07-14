package supermarket.checkout;

import supermarket.data.StockKeepingUnit;

import java.util.Objects;

public class BasketItem {
    private StockKeepingUnit stockKeepingUnit;
    private int quantity;


    public BasketItem(StockKeepingUnit stockKeepingUnit, int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("BasketItem quantity must be greater than 0");
        }

        this.stockKeepingUnit = stockKeepingUnit;
        this.quantity = quantity;
    }

    public StockKeepingUnit getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    //Generated Below

    @Override
    public String toString() {
        return "BasketItem{" +
                "stockKeepingUnit=" + stockKeepingUnit +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem basketItem = (BasketItem) o;
        return quantity == basketItem.quantity &&
                Objects.equals(stockKeepingUnit, basketItem.stockKeepingUnit);
    }

}
