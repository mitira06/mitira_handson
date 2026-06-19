import java.util.ArrayList;
import java.util.HashMap;

// Product class for inventory
class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[ID=" + productId + ", Name=" + productName + ", Qty=" + quantity + ", Price=" + price + "]";
    }
}

public class Exercise1_Inventory {

    // Using HashMap: productId -> Product
    static HashMap<Integer, Product> inventory = new HashMap<>();

    // Add product - O(1)
    static void addProduct(Product p) {
        inventory.put(p.productId, p);
        System.out.println("Added: " + p);
    }

    // Update product - O(1)
    static void updateProduct(int id, int newQty, double newPrice) {
        if (inventory.containsKey(id)) {
            inventory.get(id).quantity = newQty;
            inventory.get(id).price = newPrice;
            System.out.println("Updated product ID " + id);
        } else {
            System.out.println("Product not found!");
        }
    }

    // Delete product - O(1)
    static void deleteProduct(int id) {
        if (inventory.remove(id) != null) {
            System.out.println("Deleted product ID " + id);
        } else {
            System.out.println("Product not found!");
        }
    }

    // Display all products
    static void displayAll() {
        System.out.println("Current Inventory:");
        for (Product p : inventory.values()) {
            System.out.println("  " + p);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 1: Inventory Management System =====\n");

        addProduct(new Product(101, "Laptop", 10, 55000));
        addProduct(new Product(102, "Mouse", 50, 500));
        addProduct(new Product(103, "Keyboard", 30, 800));

        System.out.println();
        displayAll();

        System.out.println();
        updateProduct(102, 45, 450);
        deleteProduct(103);

        System.out.println();
        displayAll();

        System.out.println("\nTime Complexity:");
        System.out.println("  Add    -> O(1) average using HashMap");
        System.out.println("  Update -> O(1) average using HashMap");
        System.out.println("  Delete -> O(1) average using HashMap");
    }
}
