import java.util.HashMap;

public class Exercise7_Financial {

    // Simple Recursive: futureValue = presentValue * (1 + rate)^years
    // Time complexity: O(n) — n recursive calls
    static double futureValueRecursive(double presentValue, double rate, int years) {
        if (years == 0) return presentValue;
        return futureValueRecursive(presentValue * (1 + rate), rate, years - 1);
    }

    // Optimized with Memoization to avoid recomputation
    static HashMap<Integer, Double> memo = new HashMap<>();

    static double futureValueMemo(double presentValue, double rate, int years) {
        if (years == 0) return presentValue;
        if (memo.containsKey(years)) return memo.get(years);
        double result = futureValueMemo(presentValue, rate, years - 1) * (1 + rate);
        memo.put(years, result);
        return result;
    }

    // Iterative version for comparison
    static double futureValueIterative(double presentValue, double rate, int years) {
        double value = presentValue;
        for (int i = 0; i < years; i++) {
            value *= (1 + rate);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 7: Financial Forecasting (Recursion) =====\n");

        double presentValue = 10000;  // Rs. 10,000
        double growthRate = 0.08;     // 8% per year
        int years = 5;

        System.out.printf("Present Value : Rs. %.2f%n", presentValue);
        System.out.printf("Growth Rate   : %.0f%%%n", growthRate * 100);
        System.out.printf("Years         : %d%n%n", years);

        double recursive = futureValueRecursive(presentValue, growthRate, years);
        System.out.printf("Recursive Result     : Rs. %.2f%n", recursive);

        double memoized = futureValueMemo(presentValue, growthRate, years);
        System.out.printf("Memoized Result      : Rs. %.2f%n", memoized);

        double iterative = futureValueIterative(presentValue, growthRate, years);
        System.out.printf("Iterative Result     : Rs. %.2f%n", iterative);

        System.out.println("\nYear-by-Year Forecast:");
        double val = presentValue;
        for (int i = 1; i <= years; i++) {
            val *= (1 + growthRate);
            System.out.printf("  Year %d -> Rs. %.2f%n", i, val);
        }

        System.out.println("\nTime Complexity:");
        System.out.println("  Basic Recursive -> O(n) - one call per year");
        System.out.println("  Memoized        -> O(n) first call, O(1) for repeated calls");
        System.out.println("  Iterative       -> O(n) - most efficient in practice");
        System.out.println("\nRecursion simplifies the formula but can stack overflow for very large n.");
        System.out.println("Memoization avoids redundant computation in complex recursive problems.");
    }
}
