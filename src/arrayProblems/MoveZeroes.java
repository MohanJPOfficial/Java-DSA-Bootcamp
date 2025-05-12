package arrayProblems;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/move-zeroes/
 *
 * Input: nums = [0, 1, 4, 0, 5, 2]
 * Output: [1, 4, 5, 2, 0, 0]
 */
public class MoveZeroes {

    public static void main(String[] args) {

        int[] arr = { 0, 1, 4, 0, 5, 2 };

        System.out.print("The zeroes are moved of array " + Arrays.toString(arr) + "  is : ");

        moveZeroes(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void moveZeroes(int[] nums) {

        // If array has less than 2 elements, no need to process
        if(nums.length < 2)
            return;

        int start = 0; // Points to the current index to check for zero
        int end = 1; // Points ahead to find the next non-zero element

        while(start < nums.length && end < nums.length) {

            // If current 'start' is pointing to zero
            if(nums[start] == 0) {

                // If 'end' is pointing to a non-zero, swap with 'start'
                if(nums[end] != 0) {
                    swap(nums, start, end);

                    // After swap, move 'start' to next position
                    start++;
                }

                // Whether swapped or not, move 'end' forward to keep looking
            } else {
                // If 'start' is already at a non-zero, just move forward
                start++;
            }

            // Always move 'end' forward
            end++;
        }
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
