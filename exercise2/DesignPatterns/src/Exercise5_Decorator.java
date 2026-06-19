public class Exercise5_Decorator {

    // Component interface
    interface Notifier {
        void send(String message);
    }

    // Base component - Email (always sent)
    static class EmailNotifier implements Notifier {
        private String email;
        public EmailNotifier(String email) { this.email = email; }
        public void send(String message) {
            System.out.println("  [EMAIL] Sending to " + email + ": " + message);
        }
    }

    // Abstract decorator
    static abstract class NotifierDecorator implements Notifier {
        protected Notifier wrappedNotifier;
        public NotifierDecorator(Notifier notifier) { this.wrappedNotifier = notifier; }
        public void send(String message) { wrappedNotifier.send(message); }
    }

    // Concrete decorators - add extra channels
    static class SMSNotifierDecorator extends NotifierDecorator {
        private String phone;
        public SMSNotifierDecorator(Notifier notifier, String phone) {
            super(notifier);
            this.phone = phone;
        }
        public void send(String message) {
            super.send(message);
            System.out.println("  [SMS]   Sending to " + phone + ": " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        private String channel;
        public SlackNotifierDecorator(Notifier notifier, String channel) {
            super(notifier);
            this.channel = channel;
        }
        public void send(String message) {
            super.send(message);
            System.out.println("  [SLACK] Posting to #" + channel + ": " + message);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 5: Decorator Pattern =====\n");

        // Only Email
        System.out.println("  -- Email only --");
        Notifier notifier = new EmailNotifier("user@example.com");
        notifier.send("Your order is confirmed!");

        // Email + SMS
        System.out.println("\n  -- Email + SMS --");
        notifier = new SMSNotifierDecorator(
                       new EmailNotifier("user@example.com"),
                       "+91-9876543210");
        notifier.send("Your order has shipped!");

        // Email + SMS + Slack
        System.out.println("\n  -- Email + SMS + Slack --");
        notifier = new SlackNotifierDecorator(
                       new SMSNotifierDecorator(
                           new EmailNotifier("user@example.com"),
                           "+91-9876543210"),
                       "alerts");
        notifier.send("Server is down! Urgent!");

        System.out.println("\n  Decorator adds channels dynamically without changing original class!");
    }
}
