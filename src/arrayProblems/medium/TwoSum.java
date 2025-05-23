package arrayProblems.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Leet code -> @link https://leetcode.com/problems/two-sum/description/
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 *
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Brute Force
 * Better (Using HashMap)
 * Optimal (Using Sorting + Two Pointers)
 */
public class TwoSum {

    public static void main(String[] args) {

        int[] nums = {2, 6, 5, 8, 11};
        int target = 14;

        int[] ans = twoSumBetter(nums, target);

        System.out.println("Indices of the two numbers that sum up to " + target + " are: [" + ans[0] + ", " + ans[1] + "]");
    }

    /**
     * Brute force -> Compare all pairs
     *
     * Time Complexity:O(N^2), For using two nested loops to traverse the array, where N is the length of that array.
     *
     * Space Complexity: O(1), not using extra space.
     */
    private static int[] twoSumBruteForce(int[] nums, int target) {

        int n = nums.length;
        //create ans array to store ans
        int[] ans = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                /* if nums[i] + nums[j] is equal to
                   target put i and j in ans */
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }

        // Return {-1, -1} if no such pair is found
        return new int[] { -1, -1 };
    }

    /**
     * Better approach -> store visited values in map
     *
     * Time Complexity:O(N), where N is the size of the array. The loop runs N times in the worst case and searching in a hashmap takes O(1) generally.
     * So the time complexity is O(N).
     *
     * Note:In the worst case(which rarely happens), the unordered_map takes O(N) to find an element.
     * In that case, the time complexity will be O(N2).
     * If we use map instead of unordered_map, the time complexity will be O(N* logN) as the map data structure takes logN time to find an element.
     *
     * Space Complexity: O(N) for using the map data structure.
     */
    private static int[] twoSumBetter(int[] nums, int target) {

        // Map to store (element, index) pairs
        Map<Integer, Integer> mpp = new HashMap<>();

        // Size of the nums array
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // Current number in the array
            int num = nums[i];

            // Number needed to reach the target
            int moreNeeded = target - num;

            // Check if the complement exists in map
            if (mpp.containsKey(moreNeeded)) {
                /* Return the indices of the two
                numbers that sum up to target*/
                return new int[] { mpp.get(moreNeeded), i };
            }

            // Store current number and its index in map
            mpp.put(num, i);
        }

        // If no such pair found, return {-1, -1}
        return new int[] { -1, -1 };
    }


    /**
     * Optimal approach -> Two pointers on 2d array
     *
     * Time Complexity: O(N) + O(N*logN), where N is size of the array.
     * As the loop will run at most N times & sorting the array will take N * logN time complexity.
     *
     * Space Complexity: O(N), because of the external data structure created to store the array elements along with their indices
     */
    private static int[] twoSumOptimal(int[] nums, int target) {

        int[][] arr = new int[nums.length][2];

        // 2D array to store {element, index} pairs
        for(int i = 0; i < nums.length; i++) {
            arr[i][0] = i;
            arr[i][1] = nums[i];
        }

        /* Sort eleIndex by the first
        element in ascending order*/
        sort2dArray(arr);

        /* Two pointers: one starting
        from left and one from right */
        int start = 0;
        int end = nums.length - 1;

        while(start < end) {

            /* Calculate sum of elements at
            left and right pointers */
            int sum = arr[start][1] + arr[end][1];

            if(sum == target) {
                return new int[] { arr[start][0], arr[end][0] };
            }

            if(sum > target) {
                end--;
            } else {
                start++;
            }
        }

        return new int[] { -1, -1 };

    }

    private static void sort2dArray(int[][] arr) {

        // shortForm
        //Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });
    }
}
