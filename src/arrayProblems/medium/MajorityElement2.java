package arrayProblems.medium;

import java.util.*;

/**
 * Leet code -> @link https://leetcode.com/problems/majority-element-ii/
 * <p>
 * Given an integer array nums of size n.
 * Return all elements which appear more than n/3 times in the array. The output can be returned in any order.
 *
 * Examples:
 * Input: nums = [1, 2, 1, 1, 3, 2]
 * Output: [1]
 *
 * Explanation: Here, n / 3 = 6 / 3 = 2.
 *
 * Therefore the elements appearing 3 or more times is : [1]
 *
 * Input: nums = [1, 2, 1, 1, 3, 2, 2]
 * Output: [1, 2]
 *
 * Explanation: Here, n / 3 = 7 / 3 = 2.
 *
 * Therefore the elements appearing 3 or more times is : [1, 2]
 */
public class MajorityElement2 {

    public static void main(String[] args) {

        int[] nums = { 1, 2, 1, 1, 3, 2, 2, 3 };

        List<Integer> majorityElement = majorityElementTwoBruteForce(nums);

        System.out.println("The majority elements are: " + majorityElement);
    }

    /**
     * 🔴 Brute Force Approach -> Compare and count all element
     * <p>
     * Try each element and count how many times it appears.
     * If any count > n/3 → add that element to list.
     * <p>
     * Time: O(N2), where N is the size of the array. As for every element of the array the inner loop runs for N times.
     * Space: O(1)
     */
    private static List<Integer> majorityElementTwoBruteForce(int[] nums) {

        List<Integer> majority = new ArrayList<>();
        int n = nums.length;

        for(int i = 0; i < n; i++) {

            if(majority.contains(nums[i])) {
                continue;
            }

            int count = 1;

            for(int j = i + 1; j < n; j++) {
                if(nums[j] == nums[i]) {
                    count++;
                }
            }

            if(count > n / 3)
                majority.add(nums[i]);
        }

        Collections.sort(majority);

        return majority;
    }

    /**
     * 🟠 Better Approach – Hashing
     * <p>
     * Use a map to count how many times each number appears.
     * Then check if any value in the map > n/3.
     * <p>
     * Time: O(N log N) – N for loop, log N due to map operations
     * Space: O(N) – extra space for storing counts
     */
    private static List<Integer> majorityElementTwoBetter(int[] nums) {

        Set<Integer> majority = new TreeSet<>();
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        // because > n/3
        int minimumOccur = n / 3 + 1;

        for(int num : nums) {
            map.put(
                    num, map.getOrDefault(num, 0) + 1
            );

            if(map.get(num) == minimumOccur)
                majority.add(num);

            if(majority.size() == 2)
                break;
        }

        return new ArrayList<Integer>(majority);
    }

    /**
     * 🟢 Optimal – Moore's Voting Algorithm
     * <p>
     * 🔁 Step-by-step:
     * 1. Instead of 1 candidate, we now maintain 2 candidates.
     * 2. Think of it as a voting game with 2 thrones.
     * 3. Only those with enough votes will survive.
     * <p>
     * Why it works? If majority element exists, its count outweighs others.
     * <p>
     * Time Complexity: O(N) + O(N), where N is size of the given array. The first O(N) is to calculate the counts and find the expected majority elements. The second one is to check if the calculated elements are the majority ones or not.
     *
     * Space Complexity: O(1) for only using a list that stores a maximum of 2 elements. The space used is so small that it can be considered constant.
     */
    private static List<Integer> majorityElementTwoOptimal(int[] nums) {

        int n = nums.length;

        int element1 = Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;
        int count1 = 0;
        int count2 = 0;

        // because > n/3
        int minimumOccur = n / 3 + 1;

        for(int num : nums) {

            if(num == element1) {
                count1++;

            } else if(num == element2) {
                count2++;

            } else if(count1 == 0) {
                count1 = 1;
                element1 = num;

            } else if(count2 == 0) {
                count2 = 1;
                element2 = num;

            } else {
                count1--;
                count2--;
            }
        }

        // manual check
        count1 = 0;
        count2 = 0;

        for(int num : nums) {
            if(num == element1)
                count1++;
            else if(num == element2)
                count2++;
        }

        List<Integer> res = new ArrayList<>();

        if(count1 >= minimumOccur)
            res.add(element1);

        if(count2 >= minimumOccur)
            res.add(element2);

        return res;
    }
}
