package arrayProblems.medium;

/**
 * Tuf -> @link https://takeuforward.org/plus/dsa/arrays/faqs-medium/pascals-triangle-i
 *
 * Input: r = 4, c = 2
 * Output: 3
 * Explanation: The Pascal's Triangle is as follows:
 *     1
 *    1 1
 *   1 2 1
 *  1 3 3 1
 * ..........
 *
 * Thus, value at row 4 and column 2 = 3
 */
public class PascalArrayOne {

    public static void main(String[] args) {

        // row number
        int r = 5;
        // col number
        int c = 3;

        int ans = pascalTriangleI(r, c);

        System.out.println("The element in the rth row and cth column in pascal's triangle is: " + ans);
    }

    private static int pascalTriangleI(int r, int c) {
        // Convert to 0-based index by subtracting 1 from row and column
        return nCr(r - 1, c - 1);
    }

    /**
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
}
