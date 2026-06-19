public class Exercise2_Search {

    static class Product {
        int productId;
        String productName;
        String category;

        public Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        @Override
        public String toString() {
            return "[ID=" + productId + ", Name=" + productName + ", Category=" + category + "]";
        }
    }

    // Linear Search - O(n)
    static int linearSearch(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productId == targetId) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search - O(log n) — array must be sorted by productId
    static int binarySearch(Product[] products, int targetId) {
        int low = 0, high = products.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (products[mid].productId == targetId) return mid;
            else if (products[mid].productId < targetId) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 2: E-commerce Search Function =====\n");

        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Shirt", "Clothing"),
            new Product(103, "Book", "Education"),
            new Product(104, "Phone", "Electronics"),
            new Product(105, "Shoes", "Footwear")
        };

        int searchId = 103;

        // Linear Search
        int linResult = linearSearch(products, searchId);
        System.out.println("Linear Search for ID " + searchId + ":");
        System.out.println(linResult != -1 ? "  Found: " + products[linResult] : "  Not Found");

        // Binary Search (sorted array)
        int binResult = binarySearch(products, searchId);
        System.out.println("\nBinary Search for ID " + searchId + ":");
        System.out.println(binResult != -1 ? "  Found: " + products[binResult] : "  Not Found");

        System.out.println("\nTime Complexity:");
        System.out.println("  Linear Search -> O(n) — checks every element");
        System.out.println("  Binary Search -> O(log n) — halves search space each time");
        System.out.println("\nBig O Notation: measures worst-case growth of runtime as input grows.");
        System.out.println("  Best case Linear:  O(1) — first element matches");
        System.out.println("  Worst case Linear: O(n) — last or not found");
        System.out.println("  Binary Search always: O(log n) if sorted");
    }
}
