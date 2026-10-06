```java
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String DATA_FILE =
            "data/inventory.txt";

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final Inventory inventory =
            new Inventory();

    public static void main(String[] args) {

        loadData();

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1 -> addProduct();

                case 2 -> removeProduct();

                case 3 -> searchProduct();

                case 4 -> updateStock();

                case 5 -> inventory.displayAll();

                case 6 -> showInventoryValue();

                case 7 -> saveData();

                case 8 -> {
                    saveData();
                    System.out.println(
                            "\nGoodbye!"
                    );
                    running = false;
                }

                default ->
                        System.out.println(
                                "\nInvalid option."
                        );
            }
        }

        scanner.close();
    }

    // ========================================================
    // MENU
    // ========================================================

    private static void displayMenu() {

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "       INVENTORY MANAGEMENT SYSTEM"
        );
        System.out.println(
                "========================================"
        );

        System.out.println("1. Add product");
        System.out.println("2. Remove product");
        System.out.println("3. Search product");
        System.out.println("4. Update stock");
        System.out.println("5. Display inventory");
        System.out.println("6. Inventory value");
        System.out.println("7. Save inventory");
        System.out.println("8. Exit");

        System.out.println(
                "========================================"
        );
    }

    // ========================================================
    // ADD PRODUCT
    // ========================================================

    private static void addProduct() {

        System.out.println("\n--- Add Product ---");

        int id = readInt("Product ID: ");

        String name = readString("Product name: ");

        String category =
                readString("Category: ");

        double price =
                readDouble("Price: ");

        int quantity =
                readInt("Quantity: ");

        try {

            Product product = new Product(
                    id,
                    name,
                    category,
                    price,
                    quantity
            );

            inventory.addProduct(product);

            System.out.println(
                    "\nProduct added successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "\nError: " + e.getMessage()
            );
        }
    }

    // ========================================================
    // REMOVE PRODUCT
    // ========================================================

    private static void removeProduct() {

        System.out.println("\n--- Remove Product ---");

        int id = readInt("Product ID: ");

        boolean removed =
                inventory.removeProduct(id);

        if (removed) {

            System.out.println(
                    "Product removed successfully."
            );

        } else {

            System.out.println(
                    "Product not found."
            );
        }
    }

    // ========================================================
    // SEARCH PRODUCT
    // ========================================================

    private static void searchProduct() {

        System.out.println("\n--- Search Product ---");

        String keyword =
                readString("Search keyword: ");

        List<Product> results =
                inventory.searchByName(keyword);

        if (results.isEmpty()) {

            System.out.println(
                    "No products found."
            );

            return;
        }

        System.out.println("\nSearch results:");

        for (Product product : results) {
            System.out.println(product);
        }
    }

    // ========================================================
    // UPDATE STOCK
    // ========================================================

    private static void updateStock() {

        System.out.println("\n--- Update Stock ---");

        int id = readInt("Product ID: ");

        Product product =
                inventory.findProductById(id);

        if (product == null) {

            System.out.println(
                    "Product not found."
            );

            return;
        }

        System.out.println(
                "Current stock: " +
                product.getQuantity()
        );

        System.out.println("1. Increase stock");
        System.out.println("2. Decrease stock");

        int choice =
                readInt("Choose operation: ");

        int amount =
                readInt("Amount: ");

        try {

            if (choice == 1) {

                product.increaseStock(amount);

            } else if (choice == 2) {

                product.decreaseStock(amount);

            } else {

                System.out.println(
                        "Invalid operation."
                );

                return;
            }

            System.out.println(
                    "Stock updated successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // ========================================================
    // INVENTORY VALUE
    // ========================================================

    private static void showInventoryValue() {

        double total =
                inventory.calculateTotalValue();

        System.out.printf(
                "\nTotal inventory value: €%.2f%n",
                total
        );

        System.out.println(
                "Total products: " +
                inventory.getProductCount()
        );
    }

    // ========================================================
    // FILE OPERATIONS
    // ========================================================

    private static void saveData() {

        try {

            FileManager.saveInventory(
                    inventory,
                    DATA_FILE
            );

            System.out.println(
                    "\nInventory saved successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "\nError saving inventory: " +
                    e.getMessage()
            );
        }
    }

    private static void loadData() {

        try {

            FileManager.loadInventory(
                    inventory,
                    DATA_FILE
            );

        } catch (IOException e) {

            System.out.println(
                    "Error loading inventory: " +
                    e.getMessage()
            );
        }
    }

    // ========================================================
    // INPUT HELPERS
    // ========================================================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid integer."
                );
            }
        }
    }

    private static double readDouble(
            String message
    ) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static String readString(
            String message
    ) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }
}
```
