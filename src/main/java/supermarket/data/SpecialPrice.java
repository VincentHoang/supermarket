package supermarket.data;

public class SpecialPrice {
    private int quantity;
    private double price;

    public SpecialPrice(int quantity, double price) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Special Price quantity should be greater than 0");
        }
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialPrice that = (SpecialPrice) o;
        return quantity == that.quantity &&
                Double.compare(that.price, price) == 0;
    }

    @Override
    public String toString() {
        return "SpecialPrice{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
