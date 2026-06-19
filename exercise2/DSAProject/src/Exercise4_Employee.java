public class Exercise4_Employee {

    static class Employee {
        int employeeId;
        String name;
        String position;
        double salary;

        public Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "[ID=" + employeeId + ", Name=" + name + ", Position=" + position + ", Salary=" + salary + "]";
        }
    }

    static Employee[] employees = new Employee[10];
    static int size = 0;

    // Add - O(1)
    static void addEmployee(Employee e) {
        if (size < employees.length) {
            employees[size++] = e;
            System.out.println("Added: " + e);
        } else {
            System.out.println("Array full!");
        }
    }

    // Search - O(n)
    static Employee searchEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == id) return employees[i];
        }
        return null;
    }

    // Traverse - O(n)
    static void traverseEmployees() {
        System.out.println("All Employees:");
        for (int i = 0; i < size; i++) {
            System.out.println("  " + employees[i]);
        }
    }

    // Delete - O(n)
    static void deleteEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == id) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                System.out.println("Deleted employee ID " + id);
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 4: Employee Management System =====\n");

        addEmployee(new Employee(1, "Ravi", "Developer", 60000));
        addEmployee(new Employee(2, "Priya", "Manager", 80000));
        addEmployee(new Employee(3, "Arun", "Designer", 50000));

        System.out.println();
        traverseEmployees();

        System.out.println();
        Employee found = searchEmployee(2);
        System.out.println("Search ID 2: " + (found != null ? found : "Not Found"));

        System.out.println();
        deleteEmployee(2);
        traverseEmployees();

        System.out.println("\nTime Complexity:");
        System.out.println("  Add      -> O(1) — insert at end");
        System.out.println("  Search   -> O(n) — linear scan");
        System.out.println("  Traverse -> O(n) — visit all");
        System.out.println("  Delete   -> O(n) — find + shift elements");
        System.out.println("\nArrays have fixed size; use ArrayList for dynamic resizing.");
    }
}
