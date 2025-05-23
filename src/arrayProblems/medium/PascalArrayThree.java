package arrayProblems.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Leet code -> @link https://leetcode.com/problems/pascals-triangle/description/
 *
 * Input: n = 5
 * Output: [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
 * Explanation: The Pascal's Triangle is as follows:
 *
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 *
 * 1st Row has its value set to 1.
 * All other cells take their value as the sum of the values directly above them
 */
public class PascalArrayThree {

    public static void main(String[] args) {

        // row number
        int r = 5;

        List<List<Integer>> ans = pascalTriangleIII(r);

        System.out.println("The row " + r + " for pascal's triangle is: " + ans);
    }

    /**
     * Optimal
     *
     * Time Complexity:O(n^2) due to the nested loop structure where generateRow is called n times and
     * each call iterates up to row times which is also n in the worst case.
     *
     * Space Complexity:O(n^2) because the totalNums list stores n rows, and each row can contain up to n integers.
     */
    private static List<List<Integer>> pascalTriangleIII(int n) {
        List<List<Integer>> totalNums = new ArrayList<>();

        for (int row = 1; row <= n; row++) {
            totalNums.add(generateRow(row));
        }

        return totalNums;
    }

    /**
     * Optimal approach
     *
     * for Brute Force approach use nCr implementation in PascalArrayII program
     *
     * Time Complexity:O(r) due to a single loop that iterates 'r' times.
     * Space Complexity:O(r) because an integer array of size 'r' is allocated.
     */
    private static List<Integer> generateRow(int row) {
        List<Integer> nums = new ArrayList<>();

        // The first value in any Pascal's Triangle row is always 1
        nums.add(1);

        // Start filling the row from column 1 to (r - 1)
        for (int col = 1; col < row; col++) {

            /**
             * Use the formula: nums[col] = nums[col - 1] * (r - col) / col
             * This computes the current value based on the previous one to avoid factorial calculation
             */
            int sum = nums.get(col - 1) * (row - col) / col;
            nums.add(sum);
        }


        return nums;
    }
}
