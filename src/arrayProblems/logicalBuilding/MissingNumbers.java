package arrayProblems.logicalBuilding;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/missing-number/description/
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class MissingNumbers {

    public static void main(String[] args) {

        int[] arr = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };

        System.out.print("The zeroes are moved of array " + Arrays.toString(arr) + "  is : " + missingNumberByXor(arr));
    }

    /**
     * By frequency array - Better
     * TC -> O(2N)
     * SC -> O(N)
     */
    private static int missingNumberByFrequencies(int[] nums) {
        int N = nums.length;

        // Array to store frequencies, initialized to 0
        int[] freq = new int[N+1];

        // Storing the frequencies in the array
        for (int num : nums) {
            freq[num]++;
        }

        // Checking the frequencies for numbers 0 to N
        for (int i = 0; i <= N; i++) {
            if (freq[i] == 0) {
                return i;
            }
        }

        /* This line will never execute,
        it is just to avoid warnings */
        return -1;
    }

    /**
     * By cyclic sort -> Optimal
     * TC -> O(2N)
     * SC -> O(1)
     */
    private static int missingNumberByCyclicSort(int[] nums) {
        int i = 0;

        while(i < nums.length) {

            int correctIndex = nums[i];

            if(correctIndex < nums.length && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i)
                return i;
        }

        return nums.length;
    }

    /**
     * By sum of n natural numbers formula -> Optimal 1
     * TC -> O(N)
     * SC -> O(1) -> might lead to use long due to int size limit
     */
    private static int missingNumberByFormula(int[] nums) {
        // Calculate N from the length of nums
        int N = nums.length;

        // Summation of first N natural numbers
        int sum1 = (N * (N + 1)) / 2;

        // Summation of all elements in nums
        int sum2 = 0;
        for (int num : nums) {
            sum2 += num;
        }

        // Calculate the missing number
        int missingNum = sum1 - sum2;

        // Return the missing number
        return missingNum;
    }

    /**
     * By XOR - Bit Manipulation -> Optimal 2
     * TC -> O(N)
     * SC -> O(1)
     */
    private static int missingNumberByXor(int[] nums) {

        int xor1 = 0;
        int xor2 = 0;

        // XOR of two same numbers is 0.
        // XOR of any number with 0 is that number.
        for(int i = 0; i < nums.length; i++) {
            xor1 ^= i + 1;
            xor2 ^= nums[i];
        }

        return xor1 ^ xor2;
    }

    private static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
