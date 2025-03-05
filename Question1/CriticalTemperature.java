package Question1;

public class CriticalTemperature {

    /**
     * Calculates the least number of measurements needed to identify the critical temperature.
     *
     * @param k The number of identical samples of the material.
     * @param n The number of different temperature levels to test.
     * @return The minimum number of measurements required to determine the critical temperature.
     */
    public static int findCriticalTemperature(int k, int n) {
        // dp[i][j] stores the maximum number of temperature levels we can test 
        // with 'i' samples and 'j' measurements.
        int[][] dp = new int[k + 1][n + 1];
        
        int attempts = 0;
        
        // Keep increasing the number of attempts until the number of temperature levels we can test exceeds or equals 'n'.
        while (dp[k][attempts] < n) {
            attempts++;
            for (int i = 1; i <= k; i++) {
                // If a sample breaks at this temperature, we check below that level.
                // If it does not break, we check above that level.
                dp[i][attempts] = dp[i - 1][attempts - 1] + dp[i][attempts - 1] + 1;
            }
        }
        
        return attempts;
    }

    public static void main(String[] args) {
        // Sample test cases
        testFindCriticalTemperature(1, 2, 2);
        testFindCriticalTemperature(2, 6, 3);
        testFindCriticalTemperature(3, 14, 4);
        testFindCriticalTemperature(4, 20, 5);
        testFindCriticalTemperature(2, 10, 4);
        testFindCriticalTemperature(3, 25, 5);
    }

    /**
     * Helper method to verify the correctness of the findCriticalTemperature function.
     *
     * @param k The number of available samples.
     * @param n The number of temperature levels to test.
     * @param expected The expected number of measurements.
     */
    private static void testFindCriticalTemperature(int k, int n, int expected) {
        int result = findCriticalTemperature(k, n);
        System.out.printf("Test case: k=%d, n=%d\n", k, n);
        System.out.printf("Expected: %d, Actual: %d\n", expected, result);
        System.out.println(result == expected ? "PASSED" : "FAILED");
        System.out.println();
    }
}
