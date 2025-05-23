package arrayProblems.medium;

/**
 * Leet code -> @link https://leetcode.com/problems/maximum-subarray/description/
 *
 * Example 1:
 * Input: nums = [2, 3, 5, -2, 7, -4]
 * Output: 15
 * Explanation: Subarray from index 0 to 4 has the largest sum = 15
 *
 * Example 2:
 * Input: nums = [-2, -3, -7, -2, -10, -4]
 * Output: -2
 * Explanation: The best subarray is just one element (-2), since all are negative.
 *
 * Brute Force: Try every subarray — slow but simple.
 * Better: Avoid repeated sum calculations by building on previous subarray.
 * Kadane's: Track running sum; reset if it goes negative — fastest and cleanest.
 */
public class MaxSubArray {

    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int maxSum = maxSubArrayOptimal(nums);

        System.out.println("The max array sum is: " + maxSum);
    }

    /**
     * 🔴 Brute Force Approach
     * Try all possible subarrays and calculate their sum.
     * Time: O(N³) – 3 nested loops, each loop runs approximately N times.
     * Space: O(1) – no extra space used
     */
    private static int maxSubArrayBruteForce(int[] nums) {

        /* Initialize maximum sum with
        the smallest possible integer*/
        int maxi = Integer.MIN_VALUE;

        // Iterate over each starting index of subarrays
        for (int i = 0; i < nums.length; i++) {

            /* Iterate over each ending index
            of subarrays starting from i*/
            for (int j = i; j < nums.length; j++) {

                /* Variable to store the sum
                of the current subarray*/
                int sum = 0;

                // Calculate the sum of subarray nums[i...j]
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }

                // Update max if current subarray sum is greater
                maxi = Math.max(maxi, sum);
            }
        }

        // Return the maximum subarray sum found
        return maxi;
    }

    /**
     * 🟠 Better Approach
     * Instead of recalculating subarray sum every time, build on previous sum.
     * Time: O(N²) – 2 nested loops
     * Space: O(1)
     */
    private static int maxSubArrayBetter(int[] nums) {

        /* Initialize maximum sum with
        the smallest possible integer*/
        int maxi = Integer.MIN_VALUE;

        // Iterate over each starting index of subarrays
        for (int i = 0; i < nums.length; i++) {

            // Start a new subarray sum from i
            int sum = 0;

            // Add one element at a time to the subarray and update max
            for (int j = i; j < nums.length; j++) {

                /* Add the current element nums[j] to
                the sum i.e. sum of nums[i...j-1]*/
                sum += nums[j];

                /* Update maxi with the maximum of its current
                value and the sum of the current subarray*/
                maxi = Math.max(maxi, sum);
            }
        }

        // Return the maximum subarray sum found
        return maxi;
    }

    /**
     * 🟢 Optimal Approach – Kadane's Algorithm
     * Key Idea: If sum becomes negative, discard it (start fresh).
     * Time: O(N) – single loop
     * Space: O(1)
     */
    private static int maxSubArrayOptimal(int[] nums) {

        // Stores overall max
        int maxSum = Integer.MIN_VALUE;

        // Running sum of the current subarray
        int sum = 0;

        for (int num : nums) {

            // Add current element to the sum
            sum += num;

            // If current sum is greater than overall max, update it
            maxSum = Math.max(maxSum, sum);

            // If running sum becomes negative, discard it
            if (sum < 0)
                sum = 0;
        }

        return maxSum;
    }
}
