package binarySearchProblems;

/**
 * Leet code -> @link https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 */
public class SearchRotatedSortedArray {

    public static void main(String[] args) {
        //int[] arr = { 4, 5, 6, 7, 0, 1, 2};
        int[] arr = { 2, 9, 2, 2, 2 };
        int target = 2;

        /**
         * Approach:
         *
         * 1. Find the pivot element which is the highest element.
         * 2. Then apply BS for (start, pivot - 1) and (pivot + 1, end)
         * 3. In the array only two elements are descending
         *
         * [4, 5, 6, 7, 0, 1, 2] -> here, [7, 0]
         *
         */

        System.out.println("The target element of " + target + " is: " + searchRotatedSortedArray(arr, target));
    }

    private static int searchRotatedSortedArray(int[] arr, int target) {

        int pivotIndex = findPivotIndexWithDuplicates(arr);

        System.out.println("pivot index is: " + pivotIndex);

        /**
         * case - 1
         *
         * If pivot is not found, then the array is not rotated.
         * Proceed with normal BS.
         */
        if(pivotIndex == -1) {
            return binarySearch(arr, target, 0, arr.length);
        }

        /**
         * case - 2
         *
         * If pivot is same as target then return the pivot index
         */
        if(arr[pivotIndex] == target) {
            return pivotIndex;

        } else if(arr[0] >= target) {

            /**
             * case - 3
             *
             * arr = [4, 5, 6, 7, 0, 1, 2]
             *
             * for instance, if [target = 1] < [start = 4], do BS(pivot + 1, arr.length - 1).
             * Ignore elements before pivot elements which are all bigger than the target.
             */
            return binarySearch(arr, target, pivotIndex + 1, arr.length - 1);

        } else {

            /**
             * case - 4
             *
             * arr = [4, 5, 6, 7, 0, 1, 2]
             *
             * for instance, if [target = 6] > [start = 4], do BS(0, pivot - 1).
             * Ignore after pivot elements which are all smaller than the target.
             */
            return binarySearch(arr, target,0, pivotIndex - 1);
        }
    }

    private static int findPivotIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            /**
             * case - 1:
             *
             * when a mid element > mid + 1 element then mid the bigger that is pivot.
             *  s        m           e
             * [3, 4, 5, 6, 7, 0, 1, 2] -> here, mid > mid + 1
             */
            if(mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            /**
             * case - 2
             *
             * when a [mid - 1] element > [mid] element then [mid - 1] is the pivot
             *
             *  s        m        e
             * [5, 6, 7, 0, 1, 2, 3] -> here, mid + 1 > mid
             */
            if(mid > start && arr[mid - 1] > arr[mid]) {
                return mid - 1;
            }

            /**
             * case - 3
             *
             * This condition means all other after [mid] elements are smaller than < start element.
             * Hence, we can ignore all there elements since we are looking for larger element which is pivot
             *
             *  s        m        e
             * [5, 6, 7, 0, 1, 2, 3] -> here, start > mid
             */
            if(arr[start] > arr[mid]) {
                end = mid - 1;
            }

            /**
             * case - 4
             *
             * Which means the half array before mid is already sorted.
             * Hence, ignore these elements from start -> mid.
             *
             * If the mid is the pivot then it would be caught in case 1 and 2.
             * Hence, proving the bigger element in after [mid] element. So ignore [start] til [mid].
             *
             *  s        m           e
             * [4, 5, 6, 7, 8, 9, 0, 1] -> here, start < mid
             */
            if(arr[start] <= arr[mid]) {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static int findPivotIndexWithDuplicates(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            /**
             * case - 1: Direct pivot detection
             *
             * when a mid element > mid + 1 element then mid the bigger that is pivot.
             *  s        m           e
             * [3, 4, 5, 6, 7, 0, 1, 2] -> here, mid > mid + 1
             */
            if(mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            /**
             * case - 2: Mid - 1 is pivot
             *
             * when a [mid - 1] element > [mid] element then [mid - 1] is the pivot
             *
             *  s        m        e
             * [5, 6, 7, 0, 1, 2, 3] -> here, mid + 1 > mid
             */
            if(mid > start && arr[mid - 1] > arr[mid]) {
                return mid - 1;
            }

            /**
             * case - 3: Handle duplicates
             *
             * When elements at start, mid, and end are equal, binary search can't decide the sorted side, so:
             * Check if start is the pivot.
             * Then increment start and decrement end to skip duplicates.
             *
             * arr = [2, 2, 2, 2, 9]
             *
             * after 2 rotation
             *
             *        s     m     e
             * arr = [2, 9, 2, 2, 2]
             */
            if(arr[mid] == arr[start] && arr[mid] == arr[end]) {

                /**
                 * Check if start is pivot
                 *
                 * Note: What if these elements at start and end were the pivot??
                 * check if start is pivot
                 */
                if(start < end && arr[start] > arr[start + 1]) {
                    return start;
                }

                // skipping the duplicate
                start++;

                /**
                 * Check if end-1 is pivot
                 *
                 * check if previous element of end is pivot / larger
                 */
                if(end > start && arr[end - 1] > arr[end]) {
                    return end - 1;
                }

                // skipping the duplicate
                end--;

            } else if (arr[start] < arr[mid] || (arr[start] == arr[mid] && arr[mid] > arr[end])) {

                /**
                 *
                 * Case - 4: Left side is sorted, pivot on right
                 *
                 * Left half is sorted, so pivot must be in the right half.
                 * if array is sorted from arr[start] til arr[mid], or arr[start] == arr[mid] and arr[mid] > arr[end]
                 *
                 *  s     m     e
                 * [5, 6, 7, 5, 5] or
                 * [5, 5, 5, 1, 2]
                 *
                 * arr = [5, 6, 7, 7, 7, 1, 2]
                 * start = 0, end = 6
                 * mid = 3 → arr[mid] = 7
                 *
                 * Now:
                 * arr[start] = 5
                 * arr[mid] = 7
                 * arr[end] = 2
                 *
                 * → arr[start] < arr[mid] → left half is sorted → search right: `start = mid + 1`
                 */
                start = mid + 1;

            } else {
                /**
                 *
                 * Case - 5: Right side is sorted, Pivot on left
                 *
                 * Left half is not sorted, so pivot is likely there
                 *
                 * arr = [6, 7, 1, 2, 3, 4, 5]
                 * start = 0, end = 6
                 * mid = 3 → arr[mid] = 2
                 *
                 * arr[start] = 6
                 * arr[mid] = 2
                 * arr[end] = 5
                 *
                 * → arr[start] > arr[mid], so left is unsorted → search left: `end = mid - 1`
                 */
                end = mid - 1;
            }
        }

        // No pivot found
        return -1;
    }

    private static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {

            // int mid = (start + end / 2);
            // might be possible that (start + end) exceeds the range of int in java
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] > target) {
                end = mid - 1;
            } else if(arr[mid] < target) {
                start = mid + 1;
            }
        }
        return -1;
    }
}
