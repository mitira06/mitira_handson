import java.util.Arrays;
import java.util.Comparator;

public class Exercise6_Library {

    static class Book {
        int bookId;
        String title;
        String author;

        public Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return "[ID=" + bookId + ", Title=" + title + ", Author=" + author + "]";
        }
    }

    // Linear Search by title - O(n)
    static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    // Binary Search by title (sorted array) - O(log n)
    static Book binarySearchByTitle(Book[] books, String title) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);
            if (cmp == 0) return books[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 6: Library Management System =====\n");

        Book[] books = {
            new Book(3, "Data Structures", "Mark Allen"),
            new Book(1, "Clean Code", "Robert Martin"),
            new Book(5, "Java Programming", "Herbert Schildt"),
            new Book(2, "Design Patterns", "Gang of Four"),
            new Book(4, "Algorithms", "Cormen")
        };

        String searchTitle = "Java Programming";

        // Linear Search
        Book result1 = linearSearchByTitle(books, searchTitle);
        System.out.println("Linear Search for \"" + searchTitle + "\":");
        System.out.println(result1 != null ? "  Found: " + result1 : "  Not Found");

        // Sort for binary search
        Arrays.sort(books, Comparator.comparing(b -> b.title.toLowerCase()));

        // Binary Search
        Book result2 = binarySearchByTitle(books, searchTitle);
        System.out.println("\nBinary Search for \"" + searchTitle + "\" (sorted list):");
        System.out.println(result2 != null ? "  Found: " + result2 : "  Not Found");

        System.out.println("\nTime Complexity:");
        System.out.println("  Linear Search -> O(n) — works on unsorted data");
        System.out.println("  Binary Search -> O(log n) — requires sorted data");
        System.out.println("\nUse linear search for small/unsorted lists.");
        System.out.println("Use binary search for large sorted lists for better performance.");
    }
}
