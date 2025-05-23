package arrayProblems.medium;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/rotate-image/description/
 * <p>
 * Input: matrix = [
 * [1, 2, 3],
 * [4, 5, 6],
 * [7, 8, 9]
 * ]
 * <p>
 * Output: matrix = [
 * [7, 4, 1],
 * [8, 5, 2],
 * [9, 6, 3]
 * ]
 */
public class RotateMatrix {

    public static void main(String[] args) {

        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.print("The 90 degree of array: \n");

        rotateMatrixOptimal(arr);

        for(int[] i: arr) {
            System.out.println(Arrays.toString(i));
        }
    }

    /**
     * Brute Force approach
     *
     * Time Complexity: The time complexity is O(n*n) because of the nested loops iterating over the matrix elements,
     * more precisely it's O(2 * n * n).
     *
     * Space Complexity: The space complexity is O(n*n) due to the creation of a new matrix of the same size as the input matrix.
     */
    private static void rotateMatrixBruteForce(int[][] matrix) {

        // Create a temporary matrix to store the rotated result
        int[][] temp = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {

            int endCol = matrix[0].length - 1; // Last column index (used to calculate position in temp)

            // Loop through each column in reverse (right to left)
            for (int col = endCol; col >= 0; col--) {

                // Rotate 90 degrees clockwise: place matrix[col][row] into temp[row][newCol]
                temp[row][endCol - col] = matrix[col][row];
            }
        }

        // Copy rotated values from temp back to the original matrix
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, matrix[i], 0, temp[0].length);
        }
    }

    /**
     * Optimal approach
     *
     * Time Complexity: O(N2) +O(N2), to linearly iterate and find transpose of the matrix and another O(N2) to find the reverse of each row.
     * Space Complexity: O(1), as no extra space is being used.
     */
    private static void rotateMatrixOptimal(int[][] matrix) {

        /**
         * Step 1: Transpose the matrix (convert rows to columns)
         *     Example before transpose:
         *      [ [1, 2, 3],
         *        [4, 5, 6],
         *        [7, 8, 9] ]
         *
         *      After transpose:
         *      [ [1, 4, 7],
         *        [2, 5, 8],
         *        [3, 6, 9] ]
         */
        for(int row = 0; row < matrix.length; row++) {

            for(int col = row + 1; col < matrix[row].length; col++) {

                // Swap elements at (row, col) with (col, row)
                swap2d(matrix, row, col);
            }
        }

        /**
         * Step 2: Reverse each row
         *     Example after reversing rows of transposed matrix:
         *     [ [7, 4, 1],
         *       [8, 5, 2],
         *       [9, 6, 3] ]
         *
         *     => Matrix is now rotated 90 degrees clockwise
         */
        for (int[] arr : matrix) {
            reverseArray(arr);
        }
    }

    // Swap elements in 2D matrix at (row, col) with (col, row)
    private static void swap2d(int[][] matrix, int row, int col) {
        int temp = matrix[row][col];

        matrix[row][col] = matrix[col][row];
        matrix[col][row] = temp;
    }

    private static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while(start < end) {
            swap(arr, start, end);

            start++;
            end--;
        }
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
