package arrayProblems.hard;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/missing-number/description/
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class MissingAndRepeatedNumber {

    public static void main(String[] args) {

        int[] arr = { 3, 5, 4, 1, 1 };

        System.out.print("The repeated and missing numbers of array " + Arrays.toString(arr) + "  are : " + Arrays.toString(findMissingRepeatingNumberOptimal(arr)));
    }

    /**
     * By frequency array - Better
     * TC -> O(2N)
     * SC -> O(N)
     */
    private static int[] findMissingRepeatingNumberOptimal(int[] nums) {

        int n = nums.length;
        long missingNum = -1;
        long repeatedNum = -1;

        long sum = 0, nSum = 0, sum2 = 0, nSum2 = 0;

        for(int i : nums) {
            sum += i;
            sum2 += i * i;
        }

        nSum = (n * (n + 1)) / 2;
        nSum2 = (n * (n + 1) * ((2 * n) + 1)) / 6;

        long equation1 = sum - nSum;
        long equation2 = sum2 - nSum2;

        repeatedNum = (equation1 - equation2) / 2;
        missingNum = -(equation1 - repeatedNum);


        return new int[] { (int)repeatedNum, (int)missingNum };
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
