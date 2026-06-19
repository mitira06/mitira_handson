public class Exercise11_DependencyInjection {

    // Model
    static class Customer {
        private int id;
        private String name;
        private String email;

        public Customer(int id, String name, String email) {
            this.id    = id;
            this.name  = name;
            this.email = email;
        }

        public String toString() {
            return "Customer [ID=" + id + ", Name=" + name + ", Email=" + email + "]";
        }
    }

    // Repository interface
    interface CustomerRepository {
        Customer findCustomerById(int id);
        void saveCustomer(Customer customer);
    }

    // Concrete repository implementation
    static class CustomerRepositoryImpl implements CustomerRepository {
        // Simulated database
        private Customer[] database = {
            new Customer(1, "Alice",   "alice@gmail.com"),
            new Customer(2, "Bob",     "bob@gmail.com"),
            new Customer(3, "Charlie", "charlie@gmail.com")
        };

        public Customer findCustomerById(int id) {
            for (Customer c : database) {
                if (c.id == id) return c;
            }
            return null;
        }

        public void saveCustomer(Customer customer) {
            System.out.println("  [Repository] Saved: " + customer);
        }
    }

    // Service class - depends on CustomerRepository (injected, not created here)
    static class CustomerService {
        private CustomerRepository repository;

        // Constructor Injection - dependency is passed in
        public CustomerService(CustomerRepository repository) {
            this.repository = repository;
        }

        public void getCustomer(int id) {
            Customer c = repository.findCustomerById(id);
            if (c != null)
                System.out.println("  [Service] Found: " + c);
            else
                System.out.println("  [Service] Customer ID " + id + " not found.");
        }

        public void addCustomer(int id, String name, String email) {
            Customer c = new Customer(id, name, email);
            repository.saveCustomer(c);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 11: Dependency Injection =====\n");

        // Create repository
        CustomerRepository repo = new CustomerRepositoryImpl();

        // Inject repository into service (Dependency Injection)
        CustomerService service = new CustomerService(repo);

        System.out.println("  Looking up customers:");
        service.getCustomer(1);
        service.getCustomer(2);
        service.getCustomer(5); // not found

        System.out.println("\n  Adding a new customer:");
        service.addCustomer(4, "Diana", "diana@gmail.com");

        System.out.println("\n  DI means CustomerService does NOT create its own repository.");
        System.out.println("  It receives it from outside - easy to swap or test!");
    }
}
