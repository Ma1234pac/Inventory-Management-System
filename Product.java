```java
public class Product {

    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(int id, String name, String category,
                   double price, int quantity) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative."
            );
        }

        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative."
            );
        }

        this.quantity = quantity;
    }

    public void increaseStock(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );
        }

        quantity += amount;
    }

    public void decreaseStock(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero."
            );
        }

        if (amount > quantity) {
            throw new IllegalArgumentException(
                    "Not enough stock available."
            );
        }

        quantity -= amount;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    @Override
    public String toString() {

        return String.format(
                "ID: %-5d | %-20s | %-15s | Price: €%-8.2f | Stock: %d",
                id,
                name,
                category,
                price,
                quantity
        );
    }

    public String toFileFormat() {

        return id + ";" +
                name + ";" +
                category + ";" +
                price + ";" +
                quantity;
    }
}
```
