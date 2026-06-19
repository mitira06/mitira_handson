public class Exercise8_Strategy {

    // Strategy interface
    interface PaymentStrategy {
        void pay(double amount);
    }

    // Concrete strategies
    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;
        public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }
        public void pay(double amount) {
            System.out.println("  [Credit Card] Paid Rs." + amount +
                               " using card ending in " + cardNumber.substring(cardNumber.length() - 4));
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private String email;
        public PayPalPayment(String email) { this.email = email; }
        public void pay(double amount) {
            System.out.println("  [PayPal] Paid Rs." + amount + " via PayPal account: " + email);
        }
    }

    static class UPIPayment implements PaymentStrategy {
        private String upiId;
        public UPIPayment(String upiId) { this.upiId = upiId; }
        public void pay(double amount) {
            System.out.println("  [UPI] Paid Rs." + amount + " via UPI ID: " + upiId);
        }
    }

    // Context class - holds and executes the chosen strategy
    static class PaymentContext {
        private PaymentStrategy strategy;

        public void setStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void executePayment(double amount) {
            if (strategy == null) {
                System.out.println("  No payment method selected!");
                return;
            }
            strategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 8: Strategy Pattern =====\n");

        PaymentContext context = new PaymentContext();

        System.out.println("  Customer chose Credit Card:");
        context.setStrategy(new CreditCardPayment("1234567890123456"));
        context.executePayment(1500.00);

        System.out.println("\n  Customer switched to PayPal:");
        context.setStrategy(new PayPalPayment("customer@gmail.com"));
        context.executePayment(800.00);

        System.out.println("\n  Customer switched to UPI:");
        context.setStrategy(new UPIPayment("customer@upi"));
        context.executePayment(500.00);

        System.out.println("\n  Strategy lets us switch payment methods at runtime easily!");
    }
}
