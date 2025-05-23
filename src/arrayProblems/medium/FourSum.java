package arrayProblems.medium;

import java.util.*;

/**
 * Leet code -> @link https://leetcode.com/problems/4sum/description/
 *
 * Problem: Given an integer array `nums` and an integer `target`,
 * return all unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
 * such that a, b, c, and d are different indices and their sum is equal to the target.
 *
 * Each combination should be unique (no duplicates in the result).
 *
 * Example:
 * Input: nums = [1, -2, 3, 5, 7, 9], target = 7
 * Output: [[-2, 1, 3, 5]]
 *
 * Approaches:
 * 1. Brute Force: Try all combinations.
 * 2. Better: Use a HashSet to check for required fourth element.
 * 3. Optimal: Sort and use Two Pointer technique.
 */
public class FourSum {

    public static void main(String[] args) {

        int[] nums = {4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int target = 9;

        List<List<Integer>> ans = fourSumOptimal(nums, target);

        // Print the result
        ans.forEach(System.out::println);
    }

    /**
     * Brute Force Approach: Try all combinations of 4 numbers.
     * Time: O(N^4) - 4 nested loops.
     * Space: O(K) - To store K unique quadruplets in a set.
     */
    private static List<List<Integer>> fourSumBruteForce(int[] nums, int target) {

        //size of the array
        int n = nums.length;

        // Set to store unique quadruplets
        Set<List<Integer>> set = new HashSet<>();

        // Checking all possible quadruplets
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        // Calculate the sum of the current quadruplet
                        long sum = nums[i] + nums[j] + nums[k] + nums[l];

                        // Check if the sum matches the target
                        if (sum == target) {
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            // Sort the quadruplet to ensure uniqueness
                            Collections.sort(temp);
                            set.add(temp);
                        }
                    }
                }
            }
        }

        // Convert set to list (unique quadruplets)
        return new ArrayList<>(set);
    }

    /**
     * Better approach -> store visited values in map
     *
     * The better approach uses simple mathematics where
     * some calculative parameter is taken in RHS(right hand side) to compute the result.
     *
     * For example if a + b + c + d = target, then d = target - (a + b + c). Similar idea is used here.
     *
     * Time Complexity: O(N3 x log(M)), for using 3 nested loops and inside the loops there are some operations on the set data structure which take log(M) time complexity,
     * where N is size of the array, M is number of elements in the set.
     *
     * Space Complexity: O(2 x no. of the quadruplets)+O(N) for using a set data structure and a list to store the quads. This results in the first term
     *
     *
     * Better Approach: Fix one, use HashSet for the rest
     * Time: O(N^2 * logK)
     * Space: O(N + K)
     *
     */
    /**
     * Better Approach: Fix 3 elements, find the 4th using a HashSet.
     * Time: O(N^3 * logK) - 3 loops + set operations.
     * Space: O(K) - To store unique quadruplets.
     */
    private static List<List<Integer>> fourSumBetter(int[] nums, int target) {

        int n = nums.length;

        // Set to store unique quadruplets
        Set<List<Integer>> set = new HashSet<>();

        // Checking all possible quadruplets
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Set to store elements seen so far in the loop
                Set<Long> hashset = new HashSet<>();

                for (int k = j + 1; k < n; k++) {
                    /* Calculate the fourth element
                    needed to reach target*/
                    long sum = (long) nums[i] + nums[j] + nums[k];
                    long fourth = target - sum;

                    /* Find if fourth element exists in
                    hashset (complements seen so far)*/
                    if (hashset.contains(fourth)) {
                        // Found a quadruplet that sums up to target
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int) fourth);
                        Collections.sort(temp);
                        set.add(temp);
                    }

                    // Insert the kth element into hashset for future checks
                    hashset.add((long) nums[k]);
                }
            }
        }

        // Convert set to list (unique quadruplets)
        List<List<Integer>> ans = new ArrayList<>(set);

        return ans;
    }


    /**
     * Optimal approach -> Sort array + Two pointers approach
     * <p>
     * Time Complexity: (N3), as each of the pointers i and j, is running for approximately N times. And both the pointers start and end combined can run for approximately N times including the operation of skipping duplicates.
     * So the total time complexity will be O(N3). Here N is size of the array.
     * <p>
     * Space Complexity: O(1), no extra space is used.
     *
     * Optimal Approach: Sort the array and use two pointers.
     * Time: O(N^3) - Two loops + two-pointer traversal.
     * Space: O(1) - Extra space is not used (excluding output).
     */
    private static List<List<Integer>> fourSumOptimal(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        // Sort the input array nums
        Arrays.sort(nums);

        // Iterate through the array to find quadruplets
        for (int i = 0; i < n; i++) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < n; j++) {
                // Skip duplicates for j
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                // Two pointers approach
                int start = j + 1;
                int end = n - 1;

                while (start < end) {
                    long sum = (long) nums[i] + nums[j] + nums[start] + nums[end];

                    if (sum == target) {
                        // Found a quadruplet that sums up to target
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[start], nums[end]);
                        ans.add(temp);

                        // Skip duplicates for start and end
                        start++;
                        end--;
                        while (start < end && nums[start] == nums[start - 1]) start++;
                        while (start < end && nums[end] == nums[end + 1]) end--;
                    } else if (sum < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }

        return ans;
    }
}
