package arrayProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tuf -> @link https://leetcode.com/problems/spiral-matrix/description/
 *
 * Input: matrix = [
 *                  [1, 2, 3, 4],
 *                  [5, 6, 7, 8],
 *                  [9,10,11,12]
 *                 ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public static void main(String[] args) {

        int[][] mat = {
                {1}, {2}
                /*{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}*/
        };

        System.out.print("The spiral matrix of array ");
        for(int[] i: mat) {
            System.out.print("\n" + Arrays.toString(i));
        }
        System.out.print("  is : " + spiralMatrix(mat));
    }

    /**
     * Optimal approach
     *
     * Time Complexity:O(m*n) because each element in the m x n matrix is visited once.
     *
     * Space Complexity:O(m*n) due to the space used by the output list to store all elements of the m x n matrix.
     */
    private static List<Integer> spiralMatrix(int[][] nums) {

        List<Integer> spiral = new ArrayList<>();

        int row = nums.length;
        int col = nums[0].length;

        // Initialize boundaries for traversal
        int top = 0;
        int bottom = row - 1;
        int left = 0;
        int right = col - 1;

        while(top <= bottom && left <= right) {

            /**
             *  🔹 Traverse from left to right along the top row
             */
            for(int i = left; i <= right; i++) {
                spiral.add(nums[top][i]);
            }

            top++; // Move the top boundary down

            /**
             * 🔹 Traverse from top to bottom along the right column
             */
            for(int i = top; i <= bottom; i++) {
                spiral.add(nums[i][right]);
            }

            right--; // Move the right boundary left

            /**
             * 🔹 Traverse from right to left along the bottom row (if rows remain)
             */
            if(top <= bottom) {
                for (int i = right; i >= left; i--) {
                    spiral.add(nums[bottom][i]);
                }
            }

            bottom--; // Move the bottom boundary up

            /**
             * 🔹 Traverse from bottom to top along the left column (if columns remain)
             */
            if(left <= right) {
                for (int i = bottom; i >= top; i--) {
                    spiral.add((nums[i][left]));
                }
            }

            left++; // Move the left boundary right
        }

        return spiral;
    }
}
