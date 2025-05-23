package arrayProblems.logicalBuilding;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 *
 * Input: nums = [-2, 2, 4, 4, 4, 4, 5, 5]
 * Output: [-2, 2, 4, 5, _, _, _, _], return 5
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

        int[] arr = { 0, 1, 4, 0, 5, 2 };

        int uniqueElementsSize = removeDuplicates(arr);

        System.out.print("The uniqueElementsSize of array " + Arrays.toString(arr) + "  is : " + uniqueElementsSize);
    }

    private static int removeDuplicates(int[] nums) {

        // If the array has less than 2 elements, no duplicates are possible
        if(nums.length < 2)
            return nums.length;

        int start = 0; // Points to the last unique element
        int end = 1;   // Scans ahead to find the next unique element

        while(start < nums.length && end < nums.length) {

            // If the current element is the same as the last unique one, skip it
            if(nums[start] == nums[end]) {
                end++; // Duplicate found, just move ahead
            } else {

                // Found a new unique element at 'end'
                // Place it next to the last unique element
                swap(nums, start + 1, end);

                // Move 'start' forward to point to the new unique element
                start++;

                // Move 'end' forward to continue scanning
                end++;
            }
        }

        // Number of unique elements is 'start + 1' because index starts at 0
        return start + 1;
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
