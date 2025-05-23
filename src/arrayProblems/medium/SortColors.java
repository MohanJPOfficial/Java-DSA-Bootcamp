package arrayProblems.medium;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/sort-colors/description/
 * <p>
 * Input: nums = [1, 0, 2, 1, 0]
 * Output: [0, 0, 1, 1, 2]
 * Explanation: The nums array in sorted order has 2 zeroes, 2 ones and 1 two
 */
public class SortColors {

    public static void main(String[] args) {

        int[] nums = { 2, 0, 1, 1, 0, 2 };
        sortZeroOneTwoOptimal(nums);

        System.out.println("After array is sorted: " + Arrays.toString(nums));
    }

    /**
     * BruteForce - using sorting algorithm
     *
     * Time Complexity: O(N x logN), where N is the size of the array. As the optimal sorting take O(N * logN) time.
     *
     * Space Complexity: O(1) no extra space is used to solve the problem.
     */
    private static void sortZeroOneTwoBruteForce(int[] nums) {
        // Sort the array using Arrays.sort
        Arrays.sort(nums);
    }

    /**
     * Better approach - maintaining the frequencies in 3 variables
     *
     * Time Complexity: O(N) + O(N) = O(2N), where N is the size of the array.
     * There are 2 traversals in the array to count the frequencies then in second iteration we are overwriting.
     *
     * Space Complexity: O(1) no extra space used.
     */
    private static void sortZeroOneTwoBetter(int[] nums) {
        int zeroCount = 0, oneCount = 0, twoCount = 0;

        // Counting the number of 0s, 1s, and 2s in the array
        for(int i: nums) {
            if(i == 0)
                zeroCount++;
            else if(i == 1)
                oneCount++;
            else
                twoCount++;
        }

        /* Placing the elements in the
           original array based on counts */
        int i = 0;

        // placing 0's
        while(i < zeroCount) {
            nums[i] = 0;
            i++;
        }

        // placing 1's
        while(i < zeroCount + oneCount) {
            nums[i] = 1;
            i++;
        }

        // placing 2's
        while(i < zeroCount + oneCount + twoCount) {
            nums[i] = 2;
            i++;
        }
    }

    /**
     * Optimal approach - Dutch National Flag Algorithm
     *
     * We want to sort the array so that all 0s come first, then 1s, then 2s.
     * To do this in one pass, we use three pointers: low, mid, and high.
     *
     * - All elements before 'low' are 0.
     * - All elements between 'low' and 'mid' are 1.
     * - All elements after 'high' are 2.
     *
     * Example:
     * Input : [2, 0, 1]
     * Step 1: mid=0 → nums[mid]=2 → swap with nums[high] → [1, 0, 2], high--
     * Step 2: mid=0 → nums[mid]=1 → mid++
     * Step 3: mid=1 → nums[mid]=0 → swap with nums[low] → [0, 1, 2], low++, mid++
     * Done (mid > high)
     *
     * Time Complexity: O(N) → we loop through the array only once.
     * Space Complexity: O(1) → no extra space used.
     */
    private static void sortZeroOneTwoOptimal(int[] nums) {
        // 3 pointers: low, mid, high
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {

            // Swap 0 to the front
            if (nums[mid] == 0) {

                /* Swap nums[low] and nums[mid], then
                move both low and mid-pointers forward*/
                swap(nums, low, mid);
                low++;
                mid++;

            } else if (nums[mid] == 1) {
                // Just move mid forward
                // Move mid-pointer forward
                mid++;

            } else {

                // Swap 2 to the end

                /* Swap nums[mid] and nums[high],
                then move high pointer backward*/

                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
