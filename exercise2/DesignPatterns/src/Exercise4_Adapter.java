public class Exercise4_Adapter {

    // Target interface our app uses
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    // Existing third-party gateway classes (different interfaces)
    static class PayPalGateway {
        public void makePayment(double amount) {
            System.out.println("  [PayPal] Payment of Rs." + amount + " processed via PayPal.");
        }
    }

    static class StripeGateway {
        public void chargeCard(double amount) {
            System.out.println("  [Stripe] Card charged Rs." + amount + " via Stripe.");
        }
    }

    static class RazorpayGateway {
        public void sendPayment(double amount) {
            System.out.println("  [Razorpay] Payment of Rs." + amount + " sent via Razorpay.");
        }
    }

    // Adapter classes - bridge between our interface and third-party
    static class PayPalAdapter implements PaymentProcessor {
        private PayPalGateway paypal = new PayPalGateway();
        public void processPayment(double amount) { paypal.makePayment(amount); }
    }

    static class StripeAdapter implements PaymentProcessor {
        private StripeGateway stripe = new StripeGateway();
        public void processPayment(double amount) { stripe.chargeCard(amount); }
    }

    static class RazorpayAdapter implements PaymentProcessor {
        private RazorpayGateway razorpay = new RazorpayGateway();
        public void processPayment(double amount) { razorpay.sendPayment(amount); }
    }

    // Our app just calls processPayment() - doesn't care which gateway
    static void checkout(PaymentProcessor processor, double amount) {
        System.out.println("  Checking out...");
        processor.processPayment(amount);
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 4: Adapter Pattern =====\n");

        checkout(new PayPalAdapter(),   1500.00);
        System.out.println();
        checkout(new StripeAdapter(),   2500.00);
        System.out.println();
        checkout(new RazorpayAdapter(), 999.00);

        System.out.println("\n  Adapter lets us use different gateways with the same interface!");
    }
}
