package arrayProblems;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/rotate-array/
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 *
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 */
public class RotateArray {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

        int rotate = 3;

        System.out.print("The rotation of array " + Arrays.toString(arr) + "  is : ");

        rotate(arr, rotate);

        System.out.println(Arrays.toString(arr));
    }

    public static void rotate(int[] nums, int k) {
        // If the array has fewer than 2 elements, no rotation is needed
        if(nums.length < 2)
            return;

        // Use modulo to handle cases where k > nums.length
        k = k % nums.length;

        // Reverse the entire array
        reverseArray(nums, 0, nums.length - 1);

        // Reverse the first part of the array
        reverseArray(nums, 0, k - 1);

        // Reverse the second part of the array
        reverseArray(nums, k, nums.length - 1);
    }

    private static void reverseArray(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
