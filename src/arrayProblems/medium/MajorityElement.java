package arrayProblems.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Leet code -> @link https://leetcode.com/problems/majority-element/description/
 * <p>
 * A majority element in an array is one that appears more than n/2 times.
 * Guaranteed to exist in the given input.
 * <p>
 * Examples:
 * Input: [2, 2, 1, 1, 1, 2, 2]
 * Output: 2 → appears 4 times in 7 elements (n/2 = 3.5)
 */
public class MajorityElement {

    public static void main(String[] args) {

        int[] nums = {2, 2, 1, 1, 1, 2, 2};

        int majorityElement = majorityElementOptimal(nums);

        System.out.println("The majority element is: " + majorityElement);
    }

    /**
     * 🔴 Brute Force Approach
     * <p>
     * Try each element and count how many times it appears.
     * If any count > n/2 → return that element.
     * <p>
     * Time: O(N²)
     * Space: O(1)
     */
    private static int majorityElementBruteForce(int[] nums) {

        // Size of the given array
        int n = nums.length;

        // Iterate through each element of the array
        for (int i = 0; i < n; i++) {

            // Counter to count occurrences of nums[i]
            int cnt = 0;

            // Count the frequency of nums[i] in the array
            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    cnt++;
                }
            }

            // Check if frequency of nums[i] is greater than n/2
            if (cnt > (n / 2)) {
                // Return the majority element
                return nums[i];
            }
        }

        // Return -1 if no majority element is found
        return -1;
    }

    /**
     * 🟠 Better Approach – Hashing
     * <p>
     * Use a map to count how many times each number appears.
     * Then check if any value in the map > n/2.
     * <p>
     * Time: O(N log N) – N for loop, log N due to map operations
     * Space: O(N) – extra space for storing counts
     */
    private static int majorityElementBetter(int[] nums) {

        // Size of the given array
        int n = nums.length;

        // Hash map to store element counts
        Map<Integer, Integer> map = new HashMap<>();

        // Count occurrences of each element
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        /* Iterate through the map to
        find the majority element*/
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }

        // Return -1 if no majority element is found
        return -1;
    }

    /**
     * 🟢 Optimal – Moore's Voting Algorithm
     * <p>
     * 🔁 Step-by-step:
     * 1. Assume first element is majority (candidate).
     * 2. Increase count if same, decrease if different.
     * 3. When count = 0 → pick new candidate.
     * 4. Finally, check if that candidate really is majority.
     * <p>
     * Why it works? If majority element exists, its count outweighs others.
     * <p>
     * Time: O(N) for finding + O(N) for verifying
     * Space: O(1) → constant space
     */
    private static int majorityElementOptimal(int[] nums) {

        int element = nums[0];   // initial candidate
        int elementCount = 1;    // count starts at 1

        // 🧠 First Pass – Identify potential majority
        for (int num : nums) {
            if (num == element) {
                elementCount++;   // same element → increase count
            } else {
                elementCount--;   // different element → decrease count
            }

            // If count drops to 0 → switch candidate
            if (elementCount == 0) {
                element = num;
                elementCount = 1;
            }
        }

        // ✅ Second Pass – Confirm if it's really majority
        elementCount = 0;
        for (int num : nums) {
            if (num == element)
                elementCount++;
        }

        // If it appears more than n/2 → it's valid
        if (elementCount > nums.length / 2)
            return element;

        return -1; // No valid majority (won’t occur in this problem)
    }
}
