public class Exercise1_Singleton {

    static class Logger {
        private static Logger instance = null;
        private int logCount = 0;

        private Logger() {
            System.out.println("  Logger instance created.");
        }

        public static Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }

        public void log(String message) {
            logCount++;
            System.out.println("  [LOG #" + logCount + "] " + message);
        }

        public int getLogCount() { return logCount; }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 1: Singleton Pattern =====\n");

        Logger logger1 = Logger.getInstance();
        logger1.log("Application started");
        logger1.log("User logged in");

        Logger logger2 = Logger.getInstance();
        logger2.log("User performed an action");

        System.out.println("\n  logger1 == logger2: " + (logger1 == logger2));
        System.out.println("  Total logs: " + logger2.getLogCount());
        System.out.println("  Only ONE Logger instance was created and shared!");
    }
}
