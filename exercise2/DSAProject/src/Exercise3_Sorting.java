import java.util.Arrays;

public class Exercise3_Sorting {

    static class Order {
        int orderId;
        String customerName;
        double totalPrice;

        public Order(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return "[OrderID=" + orderId + ", Customer=" + customerName + ", Total=" + totalPrice + "]";
        }
    }

    // Bubble Sort - O(n^2)
    static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort - O(n log n) average
    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 3: Sorting Customer Orders =====\n");

        Order[] orders1 = {
            new Order(1, "Alice", 2500),
            new Order(2, "Bob", 500),
            new Order(3, "Charlie", 1200),
            new Order(4, "Diana", 850),
            new Order(5, "Eve", 3100)
        };

        // Copy for quick sort
        Order[] orders2 = Arrays.copyOf(orders1, orders1.length);

        System.out.println("Original Orders:");
        printOrders(orders1);

        bubbleSort(orders1);
        System.out.println("\nAfter Bubble Sort (by totalPrice):");
        printOrders(orders1);

        quickSort(orders2, 0, orders2.length - 1);
        System.out.println("\nAfter Quick Sort (by totalPrice):");
        printOrders(orders2);

        System.out.println("\nTime Complexity:");
        System.out.println("  Bubble Sort -> O(n^2) — slow for large data");
        System.out.println("  Quick Sort  -> O(n log n) average — much faster");
        System.out.println("\nQuick Sort is preferred because it's significantly faster on large datasets.");
    }
}
