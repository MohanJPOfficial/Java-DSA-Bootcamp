package com.mohanjp.binarySearchProblems;

/**
 * Geeks for geeks -> @link https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/
 */
public class BSInfiniteArray {

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170};
        int target = 10;

        System.out.println("The target element of " + target + " index is : " + findRangeAndSearch(arr, target));
    }

    public static int findRangeAndSearch(int[] nums, int target) {
        /**
         * First step find the range chunk by chunk
         * First range will be chunk of array size 2
         */
        int start = 0;
        int end = 1;

        /**
         * Checking the condition that the target lies in the chunked array range.
         */
        while (nums[end] < target) {
            int newStart = end + 1;

            /**
             * Double the box value.
             * end = previous end + (sizeOfChunkArray) * 2
             */
            end = end + (end - start + 1) * 2;
            start = newStart;
        }

        return binarySearch(nums, target, start, end);

        /**
         * Time complexity -
         *
         * Each step increases range size exponentially (2 → 4 → 8 → 16 → ...)
         * To reach index p (where target is located):
         * Takes log₂(p) steps
         * ⏱ Time: O(log p)
         */
    }

    private static int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {

            // int mid = (start + end / 2);
            // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;

            } else if (nums[mid] > target) {
                end = mid - 1;

            } else if (nums[mid] < target) {
                start = mid + 1;

            }
        }

        return -1;
    }
}
