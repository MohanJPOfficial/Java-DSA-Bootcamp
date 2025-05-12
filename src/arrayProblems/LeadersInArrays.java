package arrayProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tuf -> @link https://takeuforward.org/plus/dsa/arrays/faqs-medium/leaders-in-an-array
 * <p>
 * Input: nums = [1, 2, 5, 3, 1, 2]
 * Output: [5, 3, 2]
 * Explanation: 2 is the rightmost element, 3 is the largest element in the index range [3, 5],
 * 5 is the largest element in the index range [2, 5]
 */
public class LeadersInArrays {

    public static void main(String[] args) {

        int[] nums = { 1, 2, 5, 3, 1, 2 };

        System.out.print("The leaders of array " + Arrays.toString(nums) + "  is : " + leadersBruteForce(nums));
    }

    /**
     * Brute force approach -> linear search
     * <p>
     * Time Complexity:The time complexity is O(n^2) due to the nested for loops.
     *
     * Space Complexity:The space complexity is O(n) in the worst case, where 'n' is the size of the input array, this happens when all the elements are leaders.
     */
    private static ArrayList<Integer> leadersBruteForce(int[] nums) {

        ArrayList<Integer> leaderList = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {

            boolean isLeader = true;

            /**
             *  Check whether nums[i] is greater than all elements to its right
             */
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] <= nums[j]) {

                    /**
                     *  If any element to the right is greater or equal, nums[i] is not a leader
                     */
                    isLeader = false;
                    break;
                }
            }

            // If nums[i] is a leader, add it to the ans list
            if(isLeader) {
                leaderList.add(nums[i]);
            }
        }

        return leaderList;
    }

    /**
     * Optimal approach -> Use two pointers approach
     *
     * Time Complexity:O(n) because it iterates through the input array once.
     *
     * Space Complexity:O(n) in the worst case, where 'n' is the number of elements in the input array,
     * because the 'leaderList' ArrayList might store all the elements if they are all leaders.
     *
     * O(1), as extra space to store answer is not considered in some interviews.
     */
    private static ArrayList<Integer> leadersOptimal(int[] nums) {

        ArrayList<Integer> leaderList = new ArrayList<>();

        /**
         * Last element is always a leader hence setting currentMax as [Integer.MIN_VALUE]
         */
        int currentMax = Integer.MIN_VALUE;

        /**
         * Traversing from end
         */
        for(int i = nums.length - 1; i >= 0; i--) {

            // If current element is greater than the current maximum, it's a leader
            if(nums[i] > currentMax) {
                leaderList.add(nums[i]);
                currentMax = nums[i];
            }
        }

        Collections.reverse(leaderList);

        return leaderList;
    }
}
