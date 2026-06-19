public class Exercise3_Builder {

    static class Computer {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private String os;

        // Private constructor - only Builder can create Computer
        private Computer(Builder builder) {
            this.cpu     = builder.cpu;
            this.ram     = builder.ram;
            this.storage = builder.storage;
            this.gpu     = builder.gpu;
            this.os      = builder.os;
        }

        public String toString() {
            return "Computer [CPU=" + cpu + ", RAM=" + ram +
                   ", Storage=" + storage + ", GPU=" + gpu + ", OS=" + os + "]";
        }

        // Static nested Builder class
        static class Builder {
            private String cpu;
            private String ram;
            private String storage;
            private String gpu     = "No GPU";
            private String os      = "No OS";

            public Builder cpu(String cpu)         { this.cpu = cpu; return this; }
            public Builder ram(String ram)         { this.ram = ram; return this; }
            public Builder storage(String storage) { this.storage = storage; return this; }
            public Builder gpu(String gpu)         { this.gpu = gpu; return this; }
            public Builder os(String os)           { this.os = os; return this; }

            public Computer build() { return new Computer(this); }
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 3: Builder Pattern =====\n");

        // Basic computer
        Computer basic = new Computer.Builder()
            .cpu("Intel i3")
            .ram("8GB")
            .storage("256GB SSD")
            .build();
        System.out.println("  Basic Computer:\n  " + basic);

        // Gaming computer
        Computer gaming = new Computer.Builder()
            .cpu("Intel i9")
            .ram("32GB")
            .storage("1TB SSD")
            .gpu("NVIDIA RTX 4090")
            .os("Windows 11")
            .build();
        System.out.println("\n  Gaming Computer:\n  " + gaming);

        // Office computer
        Computer office = new Computer.Builder()
            .cpu("Intel i5")
            .ram("16GB")
            .storage("512GB SSD")
            .os("Ubuntu Linux")
            .build();
        System.out.println("\n  Office Computer:\n  " + office);

        System.out.println("\n  Builder lets us create different configs step by step!");
    }
}
