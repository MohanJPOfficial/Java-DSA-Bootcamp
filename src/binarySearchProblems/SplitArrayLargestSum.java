package binarySearchProblems;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/split-array-largest-sum/description/
 */
public class SplitArrayLargestSum {

    public static void main(String[] args) {

        //int[] arr = { 1, 2, 3, 4, 5 };
        int[] arr = { 7, 2, 5, 10, 8 };
//        int[] arr = {2,3,1,1,1,1,1};
        int maxSplit = 2;

        System.out.println("The minimum of maximum sub-array " + Arrays.toString(arr) + "  is : " + splitArrayLargestSumBinarySearch(arr, maxSplit));
    }

    /**
     * By bruteForce - linear search
     */
    private static int splitArrayLargestSumLinearSearch(int[] arr, int maxSplit) {

        if(maxSplit > arr.length)
            return -1;

        /**
         * Find the range
         * arr = [1, 2, 3, 4, 5]
         *
         * start = 5
         * end = 15
         */
        int start = 0;
        int end = 0;

        for (int element : arr) {
            start = Math.max(start, element);
            end += element;
        }

        /**
         * Now find minimum of maximum sub-array count on given maxSplit
         */
        for (int i = start; i <= end; i++) {

            int splitCount = sumOfSplitCount(arr, i);

            if (splitCount == maxSplit) {
                return i;
            }
        }

        return start;
    }


    /**
     * By Optimal - Binary Search
     */
    private static int splitArrayLargestSumBinarySearch(int[] arr, int maxSplit) {
        if(maxSplit > arr.length)
            return -1;

        /**
         * Find the range
         * arr = [1, 2, 3, 4, 5]
         *
         * start = 5
         * end = 15
         */
        int start = 0;
        int end = 0;

        for (int element : arr) {
            start = Math.max(start, element);
            end += element;
        }

        // binary search
        while(start <= end) {
            int mid = start + (end - start) / 2;

            int splitCount = sumOfSplitCount(arr, mid);

            if(splitCount > maxSplit) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // in this case start == end
        return start;
    }

    private static int sumOfSplitCount(int[] arr, int maxSum) {
        int sum = 0;
        int splitCount = 1; // at-least 1 split

        for (int element : arr) {

            if (sum + element <= maxSum) {
                sum += element;
            } else {
                sum = element;
                splitCount++;
            }
        }

        return splitCount;
    }
}
