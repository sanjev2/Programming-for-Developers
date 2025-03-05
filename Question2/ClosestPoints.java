package Question2;

import java.util.Arrays;

public class ClosestPoints {
    /**
     * Determines the closest pair of points based on the Manhattan distance.
     * In case of ties, it returns the lexicographically smallest pair of points.
     *
     * @param xCoords The x-coordinate values of the points.
     * @param yCoords The y-coordinate values of the points.
     * @return An array containing the indices of the closest pair of points.
     */
    public static int[] findClosestPair(int[] xCoords, int[] yCoords) {
        int n = xCoords.length; // Total number of points
        int[] result = new int[]{0, 1};  // Initialize result with the first two points
        int minDistance = Integer.MAX_VALUE; // Start with the maximum possible distance

        // Compare each point with every other point
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate the Manhattan distance between point i and point j
                int distance = Math.abs(xCoords[i] - xCoords[j]) + Math.abs(yCoords[i] - yCoords[j]);
                
                // If a smaller distance is found, or if the same distance is found with a smaller pair lexicographically
                if (distance < minDistance || (distance == minDistance && (i < result[0] || (i == result[0] && j < result[1])))) {
                    minDistance = distance; // Update the minimum distance
                    result[0] = i; // Set the first index of the closest pair
                    result[1] = j; // Set the second index of the closest pair
                }
            }
        }

        return result; // Return the indices of the closest pair
    }

    public static void main(String[] args) {
        // Example tests to validate the function
        testFindClosestPair(
            new int[]{1, 2, 3, 2, 4}, // X-coordinates of points
            new int[]{2, 3, 1, 2, 3}, // Y-coordinates of points
            new int[]{0, 3} // Expected result
        );
        testFindClosestPair(
            new int[]{1, 1, 1}, // X-coordinates of points
            new int[]{1, 1, 1}, // Y-coordinates of points
            new int[]{0, 1} // Expected result
        );
        testFindClosestPair(
            new int[]{1, 2, 3}, // X-coordinates of points
            new int[]{4, 5, 6}, // Y-coordinates of points
            new int[]{0, 1} // Expected result
        );
    }

    /**
     * A helper function to test the findClosestPair function.
     *
     * @param xCoords The x-coordinates of the points.
     * @param yCoords The y-coordinates of the points.
     * @param expected The expected indices of the closest pair.
     */
    private static void testFindClosestPair(int[] xCoords, int[] yCoords, int[] expected) {
        int[] result = findClosestPair(xCoords, yCoords); // Get the result by calling the function
        
        // Display the test case details
        System.out.printf("Test case: xCoords=%s, yCoords=%s\n", 
                          Arrays.toString(xCoords), Arrays.toString(yCoords));
        System.out.printf("Expected: %s, Actual: %s\n", 
                          Arrays.toString(expected), Arrays.toString(result));
        
        // Check and print if the test case passes or fails
        System.out.println(Arrays.equals(result, expected) ? "PASSED" : "FAILED");
        System.out.println();
    }
}
