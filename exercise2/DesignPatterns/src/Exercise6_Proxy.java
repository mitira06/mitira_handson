public class Exercise6_Proxy {

    // Subject interface
    interface Image {
        void display();
    }

    // Real object - expensive to load
    static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromServer();
        }

        private void loadFromServer() {
            System.out.println("  [RealImage] Loading '" + filename + "' from remote server... (slow!)");
        }

        public void display() {
            System.out.println("  [RealImage] Displaying '" + filename + "'");
        }
    }

    // Proxy - lazy load and cache
    static class ProxyImage implements Image {
        private String filename;
        private RealImage realImage = null; // not loaded yet

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        public void display() {
            // Load only when first needed (lazy initialization)
            if (realImage == null) {
                System.out.println("  [ProxyImage] First request - loading image...");
                realImage = new RealImage(filename);
            } else {
                System.out.println("  [ProxyImage] Using cached image (no reload needed)");
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 6: Proxy Pattern =====\n");

        Image image = new ProxyImage("vacation_photo.jpg");

        System.out.println("  First display call:");
        image.display();

        System.out.println("\n  Second display call:");
        image.display();

        System.out.println("\n  Third display call:");
        image.display();

        System.out.println("\n  Proxy loaded image only ONCE and cached it for future use!");
    }
}
