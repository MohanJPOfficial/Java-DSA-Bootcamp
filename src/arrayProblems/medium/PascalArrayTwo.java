package arrayProblems.medium;

import java.util.Arrays;

/**
 * Tuf -> @link https://takeuforward.org/plus/dsa/arrays/faqs-medium/pascals-triangle-ii
 *
 * Input: r = 5
 * Output: [1, 4, 6, 4, 1]
 * Explanation: The Pascal's Triangle is as follows:
 *
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * ....
 * Thus the 5th row is [1, 4, 6, 4, 1]
 */
public class PascalArrayTwo {

    public static void main(String[] args) {

        // row number
        int r = 5;

        int[] ans = pascalTriangleIIOptimal(r);

        System.out.println("The row " + r + " in pascal's triangle is: " + Arrays.toString(ans));
    }

    /**
     * Brute Force
     *
     * Time Complexity:The outer loop in pascalTriangleII iterates 'r' times, and the nCr function iterates 'r' times in the worst case.
     * Thus, the time complexity is O(r^2).
     *
     * Space Complexity:The pascalTriangleII function uses an array of size 'r' to store the result.
     * The nCr function uses constant space. Therefore, the space complexity is O(r).
     */
    private static int[] pascalTriangleIIBruteForce(int r) {
        int[] arr = new int[r];

        for(int i = 0; i < r; i++) {
            arr[i] = nCr(r - 1, i);
        }

        return arr;
    }

    /**
     *
     * Brute force
     *
     * Time Complexity:The time complexity is O(r), due to the loop iterating 'r' times.
     * Space Complexity:The space complexity is O(1), as it uses a constant amount of extra space.
     */
    private static int nCr(int n, int r) {
        int sum = 1; // Initialize the result to 1 (C(n, 0) = 1)

        // Calculate the combination using iterative approach
        /**
         * C(5, 3) - Step Expression Result	Explanation
         * Initial	sum = 1	sum = 1	Start with an initial value of sum = 1.
         * Iteration 1	sum = sum * (5 - 1 + 1) → sum = 1 * 5	sum = 5	Multiply by (n - i + 1) → 5. Then divide by 1: sum = 5 / 1 = 5.
         * Iteration 2	sum = sum * (5 - 2 + 1) → sum = 5 * 4	sum = 20	Multiply by (n - i + 1) → 4. Then divide by 2: sum = 20 / 2 = 10.
         * Iteration 3	sum = sum * (5 - 3 + 1) → sum = 10 * 3	sum = 30	Multiply by (n - i + 1) → 3. Then divide by 3: sum = 30 / 3 = 10.
         * Final ->	Return sum	10	After 3 iterations, the result is C(5, 3) = 10. This is the number of ways to choose 3 items from 5.
         */
        for(int i = 1; i <= r; i++) {

            // Multiply the sum by (n - i + 1) for the numerator
            sum = sum * (n - i + 1);

            // Divide the sum by i for the denominator to get the combination value
            sum = sum / i;
        }

        // Return the computed combination value C(n, r)
        return sum;
    }

    /**
     * Time Complexity:O(r) due to a single loop that iterates 'r' times.
     * Space Complexity:O(r) because an integer array of size 'r' is allocated.
     */
    private static int[] pascalTriangleIIOptimal(int r) {
        int[] nums = new int[r];

        // The first value in any Pascal's Triangle row is always 1
        nums[0] = 1;

        // Start filling the row from column 1 to (r - 1)
        for(int col = 1; col < r; col++) {

            /**
             * Use the formula: nums[col] = nums[col - 1] * (r - col) / col
             * This computes the current value based on the previous one to avoid factorial calculation
             */
            nums[col] = nums[col - 1] * (r - col) / col;
        }

        return nums;
    }
}
