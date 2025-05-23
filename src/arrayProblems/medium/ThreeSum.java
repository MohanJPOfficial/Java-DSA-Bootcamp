package arrayProblems.medium;

import java.util.*;

/**
 * Leet code -> @link https://leetcode.com/problems/3sum/
 *
 * Problem: Find all unique triplets in an array that sum to 0.
 *
 * For example:
 * Input: nums = [2, -2, 0, 3, -3, 5]
 * Output: [[-2, 0, 2], [-3, -2, 5], [-3, 0, 3]]
 *
 * Why? Because:
 * - (-2) + 0 + 2 = 0
 * - (-3) + (-2) + 5 = 0
 * - (-3) + 0 + 3 = 0
 *
 * Triplets must have different indices and avoid duplicates.
 *
 * Approaches:
 * 1. Brute Force: Try all combinations.
 * 2. Better: Use a HashSet to check for required third element.
 * 3. Optimal: Sort and use Two Pointer technique.
 */
public class ThreeSum {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> ans = threeSumOptimal(nums);

        // Print the result
        ans.forEach(System.out::println);
    }

    /**
     * Brute Force: Try all combinations of 3 elements.
     * Time: O(N^3 * logK), where K is number of unique triplets
     * Space: O(K) for storing unique triplets
     */
    private static List<List<Integer>> threeSumBruteForce(int[] nums) {

        // Set to store unique triplets
        Set<List<Integer>> tripletSet = new HashSet<>();

        int n = nums.length;

        // Check all possible triplets
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // Found a triplet that sums up to target
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);

                        /* Sort the triplet to ensure
                        uniqueness when storing in set*/
                        Collections.sort(temp);
                        tripletSet.add(temp);
                    }
                }
            }
        }

        // Convert set to list of lists (unique triplets)
        List<List<Integer>> ans = new ArrayList<>(tripletSet);

        //Return the ans
        return ans;
    }

    /**
     * Better approach -> store visited values in map
     *
     * The better approach uses simple mathematics where
     * some calculative parameter is taken in RHS(right hand side) to compute the result.
     *
     * For example if a + b + c = 0, then c = 0 - (a + b). Similar idea is used here.
     *
     * Time Complexity: O(N^2 x log(no. of unique triplets)), where N is size of the array.
     * Inserting triplets into the set takes O(log(no. of unique triplets)) time complexity.
     *
     * Space Complexity:  O(2 x no. of the unique triplets) + O(N) for using a set data structure and a list to store the triplets and extra O(N)
     * for storing the array elements in another set.
     *
     *
     * Better Approach: Fix one, use HashSet for the rest
     * Time: O(N^2 * logK)
     * Space: O(N + K)
     *
     */
    private static List<List<Integer>> threeSumBetter(int[] nums) {

        // Set to store unique triplets
        Set<List<Integer>> tripletSet = new HashSet<>();

        int n = nums.length;

        // Check all possible triplets
        for (int i = 0; i < n; i++) {
            // Set to store elements seen so far in the loop
            Set<Integer> hashset = new HashSet<>();

            for (int j = i + 1; j < n; j++) {
                // Calculate the 3rd element needed to reach 0
                int third =  - (nums[i] + nums[j]);

                /* Find if third element exists in
                hashset (complements seen so far)*/
                if (hashset.contains(third)) {
                    // Found a triplet that sums up to target
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(third);

                    /* Sort the triplet to ensure
                    uniqueness when storing in set*/
                    Collections.sort(temp);
                    tripletSet.add(temp);
                }

                /* Insert the current element
                 into hashset for future checks*/
                hashset.add(nums[j]);
            }
        }

        // Convert set to list of lists (unique triplets)
        List<List<Integer>> ans = new ArrayList<>(tripletSet);

        //Return the ans
        return ans;
    }


    /**
     * Optimal approach -> Sort array + Two pointers approach
     * <p>
     * Time Complexity: O(NlogN) + O(N2), where N is size of the array. As the pointer i, is running for approximately N times.
     * And both the pointers stat and end combined can run for approximately N times including the operation of skipping duplicates.
     * So the total time complexity will be O(N2).
     * <p>
     * Space Complexity: O(1), no extra space is used.
     *
     * Optimal Approach: Sort + Two Pointer
     * Time: O(N^2)
     * Space: O(1) (excluding result)
     */
    private static List<List<Integer>> threeSumOptimal(int[] nums) {

        // List to store the triplets that sum up to target
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        // Sort the input array nums
        Arrays.sort(nums);

        // Iterate through the array to find triplets
        for (int i = 0; i < n; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Two pointers approach
            int start = i + 1;
            int end = n - 1;

            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];

                if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                } else {
                    // Found a triplet that sums up to target
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[start]);
                    temp.add(nums[end]);
                    ans.add(temp);

                    // Skip duplicates
                    start++;
                    end--;
                    while (start < end && nums[start] == nums[start - 1]) start++;
                    while (start < end && nums[end] == nums[end + 1]) end--;
                }
            }
        }

        return ans;
    }
}
