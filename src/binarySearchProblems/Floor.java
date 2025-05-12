package com.mohanjp.binarySearchProblems;

/**
 * Floor is the greatest number of less than or equal to the target element.
 * Find the greatest number of less than or equal to the target element in the sorted array
 */
public class Floor {

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 9, 14, 16, 18 };
        int target = 4;

        System.out.println("The target element of " + target + " floor is : " + ceiling(arr, target));
    }

    private static int ceiling(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        /**
         * what if the element is less than all elements
         * arr = { 2, 3, 5, 9, 14, 16, 18 }, target = 1
         * in the end start will violate the while loop (end = -1 >= start = 0) condition fails.,
         * start = 0th index
         * mid = 0
         * end = -1
         * which is arrayIndexOutOfBound Exception due to -1
         */
        if(target < arr[start])
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
         * Here is the exact target element is not found and also end index crossed next to start index
         * Hence, target is end value
         */
        return arr[end];
    }
}
