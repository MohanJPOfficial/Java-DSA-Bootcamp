package com.mohanjp.binarySearchProblems;

/**
 * Leet code -> @link https://leetcode.com/problems/peak-index-in-a-mountain-array/
 */
public class PeakIndexInMountainArray {

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 9, 14, 16, 18, 15, 12, 10, 8, 3, 1 };

        int index = peakIndexInMountainArray(arr);
        System.out.println("The peak element index is " + index + " and element is " + arr[index]);
    }

    private static int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // int mid = (start + end / 2);
            // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            /**
             * You are in decreasing part of the array
             * this may be the answer, but look at left
             * that's why end != mid - 1
             */
            if (arr[mid] >= arr[mid + 1]) {
                end = mid;
            } else if(arr[mid] < arr[mid + 1]) {

                /**
                 * You are in ascending part of the array
                 * because we know that mid + 1 element > mid-element
                 */
                start = mid + 1;
            }
        }

        /**
         * In the end, start == end and pointing to the largest number because of the 2 checks above.
         * Start and end always trying to find max element in  the above 2 checks
         * Hence, when they are pointing to just one element, that's the max one because that is what the checks say.
         *
         * More elaborate:
         * At every point of time for start and end, they have the best possible answer till that time.
         * If we are saying that only one item is remaining, hence cuz of above line that i the best possible answer.
         */
        return start; // or return end as both are equal.
    }
}
