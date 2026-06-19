public class Exercise2_FactoryMethod {

    // Document interface
    interface Document {
        void open();
        void save();
    }

    // Concrete document classes
    static class WordDocument implements Document {
        public void open() { System.out.println("  Opening Word Document (.docx)"); }
        public void save() { System.out.println("  Saving Word Document (.docx)"); }
    }

    static class PdfDocument implements Document {
        public void open() { System.out.println("  Opening PDF Document (.pdf)"); }
        public void save() { System.out.println("  Saving PDF Document (.pdf)"); }
    }

    static class ExcelDocument implements Document {
        public void open() { System.out.println("  Opening Excel Document (.xlsx)"); }
        public void save() { System.out.println("  Saving Excel Document (.xlsx)"); }
    }

    // Abstract factory
    static abstract class DocumentFactory {
        public abstract Document createDocument();

        public void openAndSave() {
            Document doc = createDocument();
            doc.open();
            doc.save();
        }
    }

    // Concrete factories
    static class WordFactory extends DocumentFactory {
        public Document createDocument() { return new WordDocument(); }
    }

    static class PdfFactory extends DocumentFactory {
        public Document createDocument() { return new PdfDocument(); }
    }

    static class ExcelFactory extends DocumentFactory {
        public Document createDocument() { return new ExcelDocument(); }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 2: Factory Method Pattern =====\n");

        DocumentFactory factory;

        System.out.println("  Creating Word Document:");
        factory = new WordFactory();
        factory.openAndSave();

        System.out.println("\n  Creating PDF Document:");
        factory = new PdfFactory();
        factory.openAndSave();

        System.out.println("\n  Creating Excel Document:");
        factory = new ExcelFactory();
        factory.openAndSave();

        System.out.println("\n  Factory creates objects without knowing exact class at compile time!");
    }
}
