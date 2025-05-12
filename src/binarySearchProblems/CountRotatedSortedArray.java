package binarySearchProblems;

import java.util.Arrays;

/**
 * Gfg -> @link https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/
 *
 */
public class CountRotatedSortedArray {

    public static void main(String[] args) {
        //int[] arr = { 4, 5, 6, 7, 0, 1, 2};
        //int[] arr = { 2, 9, 2, 2, 2 };
        //int[] arr = {1, 2, 3, 4, 5};
        int[] arr = { 4, 4, 4, 1, 2, 4, 4 };

        int rotationCount = findPivotIndexWithDuplicates(arr) + 1;
        System.out.println("The rotation count of " + Arrays.toString(arr) + " is: " + rotationCount);
    }

    private static int findPivotIndexWithDuplicates(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            /**
             * case - 1
             *
             * if mid > mid + 1 then mid is the pivot
             */
            if(mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            /**
             * case - 2
             *
             * if mid - 1 > mid then [mid - 1] is the pivot
             */
            if(mid > start && arr[mid - 1] > arr[mid]) {
                return mid - 1;
            }

            // Let's handle duplicates
            /**
             * case - 3
             *
             * if start == mid == end are equal then remove duplicates from start and end.
             */
            if(arr[start] == arr[mid] && arr[mid] == arr[end]) {

                /**
                 * Before removing, check start is pivot
                 */
                if(start < end && arr[start] > arr[start + 1]) {
                    return start;
                } else {
                    /**
                     * If not then remove the duplicate from start
                     */
                    start++;
                }

                /**
                 * Let's do same thing for last before element end - 1
                 */
                if(end > start && arr[end - 1] > arr[end]) {
                    return end - 1;
                } else {
                    /**
                     * If not then remove the duplicate from end
                     */
                    end--;
                }

            } else if(arr[start] < arr[mid] || (arr[start] == arr[mid] && arr[mid] > arr[end])) {
                /**
                 * case - 4, Lets skip the left sorted array before mid, because pivot will be in right side coz of ambiguity array(2 descending elements) if left is sorted
                 */
                start = mid + 1;
            } else {
                /**
                 * arr[start] > arr[mid] || arr[start] == arr[mid] && arr[mid] <= arr[end]
                 * case - 5, Lets skip the right sorted array after mid, coz pivot will be in left side due to ambiguity array(2 descending elements) if right is sorted
                 */
                end = mid - 1;
            }
        }
        // No pivot found
        return -1;
    }
}
