package Question1;

import java.util.Arrays;

public class KthSmallestProduct {

    /**
     * Determines the kth smallest product formed by elements from two sorted arrays.
     *
     * @param returns1 The first sorted array containing investment return values.
     * @param returns2 The second sorted array containing investment return values.
     * @param k The position of the desired smallest product.
     * @return The kth smallest product from the combination of the two arrays.
     */
    public static long kthSmallestProduct(int[] returns1, int[] returns2, long k) {
        long left = (long) Math.min(
            Math.min(returns1[0] * (long)returns2[0], returns1[0] * (long)returns2[returns2.length - 1]),
            Math.min(returns1[returns1.length - 1] * (long)returns2[0], returns1[returns1.length - 1] * (long)returns2[returns2.length - 1])
        );
        long right = (long) Math.max(
            Math.max(returns1[0] * (long)returns2[0], returns1[0] * (long)returns2[returns2.length - 1]),
            Math.max(returns1[returns1.length - 1] * (long)returns2[0], returns1[returns1.length - 1] * (long)returns2[returns2.length - 1])
        );
        
        // Keep narrowing down the range by adjusting the left and right boundaries
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (countSmallerOrEqual(returns1, returns2, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    /**
     * Calculates how many products are smaller than or equal to the given target value.
     *
     * @param returns1 The first sorted array of investment return values.
     * @param returns2 The second sorted array of investment return values.
     * @param target The target product value to compare.
     * @return The number of products that are less than or equal to the target.
     */
    private static long countSmallerOrEqual(int[] returns1, int[] returns2, long target) {
        long count = 0;
        int n = returns2.length;
        for (int num : returns1) {
            if (num == 0) {
                if (target >= 0) count += n;
            } else if (num > 0) {
                count += upperBound(returns2, (double)target / num);
            } else { // num < 0
                count += n - upperBound(returns2, (double)target / num);
            }
        }
        return count;
    }

    /**
     * Finds the upper bound (the position where a target value could be inserted) in a sorted array.
     *
     * @param arr The array where insertion position is sought.
     * @param target The value to find the insertion index for.
     * @return The index where the target could be inserted while maintaining order.
     */
    private static int upperBound(int[] arr, double target) {
        int left = 0, right = arr.length;
        // Perform binary search to find the right insertion index.
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        // Sample test cases to validate the function
        testKthSmallestProduct(new int[]{2, 5}, new int[]{3, 4}, 2, 8);
        testKthSmallestProduct(new int[]{-4, -2, 0, 3}, new int[]{2, 4}, 6, 0);
        
    }

    /**
     * A helper method to test and verify the kthSmallestProduct function.
     *
     * @param returns1 The first array of investment returns.
     * @param returns2 The second array of investment returns.
     * @param k The index of the kth smallest product.
     * @param expected The expected outcome of the test.
     */
    private static void testKthSmallestProduct(int[] returns1, int[] returns2, long k, long expected) {
        long result = kthSmallestProduct(returns1, returns2, k);
        System.out.printf("Test case: returns1=%s, returns2=%s, k=%d\n", 
                          Arrays.toString(returns1), Arrays.toString(returns2), k);
        System.out.printf("Expected: %d, Actual: %d\n", expected, result);
        System.out.println(result == expected ? "PASSED" : "FAILED");
        System.out.println();
    }
}
