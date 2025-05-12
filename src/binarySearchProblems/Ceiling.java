package com.mohanjp.binarySearchProblems;

/**
 * Ceiling is the smallest number of greater than or equal to the target element.
 * Find the smallest number of greater than or equal to the target element in the sorted array
 */
public class Ceiling {

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 9, 14, 16, 18 };
        int target = 1;

        System.out.println("The target element of " + target + " ceiling is : " + ceiling(arr, target));
    }

    private static int ceiling(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        /**
         * what if the element is greater than all elements
         * arr = { 2, 3, 5, 9, 14, 16, 18 }, target = 19
         * in the end start will violate the while loop (end = 6 >= start = 7) condition fails.,
         * start = 7th index
         * mid = 6
         * end = 6
         * which is arrayIndexOutOfBound Exception
         */
        if(target > arr[end])
            return -1;

        while (start <= end) {
            // int mid = (start + end / 2);
            // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            }

            if (arr[mid] > target) {
                end = mid - 1;
            } else if(arr[mid] < target) {
                start = mid + 1;
            }
        }

        /**
         * Here is the exact target element is not found and also start index crossed next to end index
         * Hence, target is start
         */
        return arr[start];
    }
}
