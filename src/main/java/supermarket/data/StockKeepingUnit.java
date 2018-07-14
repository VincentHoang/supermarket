package supermarket.data;

import java.util.Objects;
import java.util.Optional;

public class StockKeepingUnit {

    private String itemID;
    private double unitPrice;
    private Optional<SpecialPrice> specialPrice;

    public StockKeepingUnit(String itemID, double unitPrice) {
        this(itemID, unitPrice, null);
    }

    public StockKeepingUnit(String itemID, double unitPrice, SpecialPrice specialPrice) {
        this.itemID = itemID;
        this.unitPrice = unitPrice;
        this.specialPrice = Optional.ofNullable(specialPrice);
    }

    public String getItemID() {
        return itemID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Optional<SpecialPrice> getSpecialPrice() {
        return specialPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockKeepingUnit that = (StockKeepingUnit) o;
        return Double.compare(that.getUnitPrice(), getUnitPrice()) == 0 &&
                Objects.equals(getItemID(), that.getItemID()) &&
                Objects.equals(getSpecialPrice(), that.getSpecialPrice());
    }

    @Override
    public String toString() {
        return "StockKeepingUnit{" +
                "itemID='" + itemID + '\'' +
                ", unitPrice=" + unitPrice +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
