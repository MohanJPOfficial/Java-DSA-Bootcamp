package arrayProblems.medium;

import java.util.Arrays;

/**
 * Tuf -> @link https://takeuforward.org/plus/dsa/arrays/faqs-medium/rearrange-array-elements-by-sign
 *
 * Input : nums = [2, 4, 5, -1, -3, -4]
 * Output : [2, -1, 4, -3, 5, -4]
 * Explanation: The positive number 2, 4, 5 maintain their relative positions and -1, -3, -4 maintain their relative positions
 */
public class RearrangeArray {

    public static void main(String[] args) {

        int[] nums = { 2, 4, 5, -1, -3, -4 };

        System.out.print("The rearranged of array " + Arrays.toString(nums) + "  is : " + Arrays.toString(rearrangeArrayBruteForce(nums)));
    }

    /**
     * Brute force approach -> linear search
     * <p>
     * Time Complexity:The time complexity is O(n^2) due to the nested for loops.
     *
     * Space Complexity:The space complexity is O(n) in the worst case, where 'n' is the size of the input array, this happens when all the elements are leaders.
     */
    private static int[] rearrangeArrayBruteForce(int[] nums) {

        int[] positives = new int[nums.length / 2];
        int[] negatives = new int[nums.length / 2];
        int[] result = new int[nums.length];

        int pIndex = 0;
        int nIndex = 0;

        // Segregate the array into positives and negatives.
        for(int i : nums) {
            if(i > 0) {
                positives[pIndex] = i;
                pIndex++;
            } else {
                negatives[nIndex] = i;
                nIndex++;
            }
        }

        // Combine positives and negatives alternatively
        int index = 0;
        for(int i = 0; i < nums.length; i+=2) {
            result[i] = positives[index];
            result[i + 1] = negatives[index];

            index++;
        }

        return result;
    }

    /**
     * Optimal approach -> Use two pointers approach
     *
     * Time Complexity:O(n), due to iterating through the input array `nums` once.
     *
     * Space Complexity:O(n), due to the creation of the `result` array of size n.
     */
    private static int[] rearrangeArrayOptimal(int[] nums) {

        int[] result = new int[nums.length];

        int pIndex = 0;
        int nIndex = 1;

        for(int i : nums) {

            /**
             *  If current element is positive, place it at the next even index in result
             */
            if(i > 0) {
                result[pIndex] = i;
                pIndex += 2;
            } else {
                result[nIndex] = i;
                nIndex += 2;
            }
        }

        return result;
    }
}
