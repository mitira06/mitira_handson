import java.util.ArrayList;
import java.util.List;

public class Exercise7_Observer {

    // Observer interface
    interface Observer {
        void update(String stockName, double price);
    }

    // Subject interface
    interface Stock {
        void registerObserver(Observer o);
        void deregisterObserver(Observer o);
        void notifyObservers();
    }

    // Concrete subject
    static class StockMarket implements Stock {
        private List<Observer> observers = new ArrayList<>();
        private String stockName;
        private double price;

        public StockMarket(String stockName, double price) {
            this.stockName = stockName;
            this.price     = price;
        }

        public void registerObserver(Observer o) {
            observers.add(o);
            System.out.println("  [StockMarket] Observer registered: " + o.getClass().getSimpleName());
        }

        public void deregisterObserver(Observer o) {
            observers.remove(o);
            System.out.println("  [StockMarket] Observer removed: " + o.getClass().getSimpleName());
        }

        public void notifyObservers() {
            for (Observer o : observers) {
                o.update(stockName, price);
            }
        }

        public void setPrice(double newPrice) {
            System.out.println("\n  [StockMarket] " + stockName + " price changed: Rs." + price + " -> Rs." + newPrice);
            this.price = newPrice;
            notifyObservers();
        }
    }

    // Concrete observers
    static class MobileApp implements Observer {
        public void update(String stockName, double price) {
            System.out.println("  [MobileApp ] Alert! " + stockName + " is now Rs." + price);
        }
    }

    static class WebApp implements Observer {
        public void update(String stockName, double price) {
            System.out.println("  [WebApp    ] Dashboard updated: " + stockName + " = Rs." + price);
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 7: Observer Pattern =====\n");

        StockMarket tcs = new StockMarket("TCS", 3500.00);

        MobileApp mobile = new MobileApp();
        WebApp    web    = new WebApp();

        tcs.registerObserver(mobile);
        tcs.registerObserver(web);

        tcs.setPrice(3620.00);
        tcs.setPrice(3580.00);

        System.out.println();
        tcs.deregisterObserver(mobile);
        tcs.setPrice(3700.00);

        System.out.println("\n  Observer notifies all clients automatically when data changes!");
    }
}
