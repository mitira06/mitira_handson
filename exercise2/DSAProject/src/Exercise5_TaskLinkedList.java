public class Exercise5_TaskLinkedList {

    static class Task {
        int taskId;
        String taskName;
        String status;
        Task next;

        public Task(int taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
            this.next = null;
        }

        @Override
        public String toString() {
            return "[ID=" + taskId + ", Name=" + taskName + ", Status=" + status + "]";
        }
    }

    static Task head = null;

    // Add at end - O(n)
    static void addTask(Task t) {
        if (head == null) {
            head = t;
        } else {
            Task curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = t;
        }
        System.out.println("Added: " + t);
    }

    // Search - O(n)
    static Task searchTask(int id) {
        Task curr = head;
        while (curr != null) {
            if (curr.taskId == id) return curr;
            curr = curr.next;
        }
        return null;
    }

    // Traverse - O(n)
    static void traverseTasks() {
        System.out.println("All Tasks:");
        Task curr = head;
        while (curr != null) {
            System.out.println("  " + curr);
            curr = curr.next;
        }
    }

    // Delete - O(n)
    static void deleteTask(int id) {
        if (head == null) { System.out.println("List empty!"); return; }
        if (head.taskId == id) { head = head.next; System.out.println("Deleted task ID " + id); return; }
        Task curr = head;
        while (curr.next != null && curr.next.taskId != id) curr = curr.next;
        if (curr.next != null) {
            curr.next = curr.next.next;
            System.out.println("Deleted task ID " + id);
        } else {
            System.out.println("Task not found!");
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 5: Task Management System (Linked List) =====\n");

        addTask(new Task(1, "Design UI", "Pending"));
        addTask(new Task(2, "Write Code", "In Progress"));
        addTask(new Task(3, "Test App", "Pending"));

        System.out.println();
        traverseTasks();

        System.out.println();
        Task found = searchTask(2);
        System.out.println("Search ID 2: " + (found != null ? found : "Not Found"));

        System.out.println();
        deleteTask(2);
        traverseTasks();

        System.out.println("\nTime Complexity:");
        System.out.println("  Add      -> O(n) to reach end, O(1) if tracking tail");
        System.out.println("  Search   -> O(n)");
        System.out.println("  Traverse -> O(n)");
        System.out.println("  Delete   -> O(n)");
        System.out.println("\nLinked List advantage: No fixed size, easy insert/delete at any point.");
    }
}
