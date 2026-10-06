```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveInventory(
            Inventory inventory,
            String filename
    ) throws IOException {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filename)
                     )) {

            for (Product product : inventory.getProducts()) {

                writer.write(product.toFileFormat());
                writer.newLine();
            }
        }
    }

    public static void loadInventory(
            Inventory inventory,
            String filename
    ) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(file)
                     )) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(";");

                if (data.length != 5) {
                    continue;
                }

                try {

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String category = data[2];
                    double price = Double.parseDouble(data[3]);
                    int quantity = Integer.parseInt(data[4]);

                    Product product = new Product(
                            id,
                            name,
                            category,
                            price,
                            quantity
                    );

                    inventory.addProduct(product);

                } catch (NumberFormatException |
                         IllegalArgumentException e) {

                    System.out.println(
                            "Warning: Invalid product data skipped."
                    );
                }
            }
        }
    }
}
```
