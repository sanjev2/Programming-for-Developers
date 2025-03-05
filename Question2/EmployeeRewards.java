package Question2;

import java.util.Arrays; // Importing Arrays for convenient array operations

public class EmployeeRewards {
    /**
     * Calculates the smallest number of rewards to distribute based on employee ratings.
     *
     * @param ratings An array representing the performance ratings of employees.
     * @return The minimum total number of rewards required.
     */
    public static int minRewards(int[] ratings) {
        int n = ratings.length; // Number of employees
        int[] rewards = new int[n]; // Array to store the rewards for each employee
        Arrays.fill(rewards, 1);  // Assign a baseline reward of 1 for every employee

        // First pass: examine ratings from left to right
        for (int i = 1; i < n; i++) { // Traverse employees from left to right
            if (ratings[i] > ratings[i - 1]) { // Check if the current employee has a higher rating than the previous
                rewards[i] = rewards[i - 1] + 1; // Grant an additional reward to the current employee
            }
        }

        // Second pass: examine ratings from right to left
        for (int i = n - 2; i >= 0; i--) { // Traverse employees from right to left
            if (ratings[i] > ratings[i + 1]) { // Check if the current employee has a higher rating than the next
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1); // Ensure the current employee gets more rewards than the next one
            }
        }

        // Calculate the total number of rewards by summing the array
        return Arrays.stream(rewards).sum(); // Return the total rewards required for all employees
    }

    public static void main(String[] args) {
        // Sample test cases to validate the solution
        testMinRewards(new int[]{1, 0, 2}, 5); // First test case
        testMinRewards(new int[]{1, 2, 2}, 4); // Second test case
        testMinRewards(new int[]{1, 2, 3, 4, 5}, 15); // Third test case
    }

    /**
     * Helper method to check the correctness of the minRewards function.
     *
     * @param ratings The ratings array for the employees.
     * @param expected The expected result for the minimum number of rewards.
     */
    private static void testMinRewards(int[] ratings, int expected) {
        int result = minRewards(ratings); // Call the minRewards function
        System.out.printf("Test case: ratings=%s\n", Arrays.toString(ratings)); // Output the ratings array
        System.out.printf("Expected: %d, Actual: %d\n", expected, result); // Output the expected vs actual results
        System.out.println(result == expected ? "PASSED" : "FAILED"); // Print the result status (Pass/Fail)
        System.out.println(); // Empty line for readability
    }
}
