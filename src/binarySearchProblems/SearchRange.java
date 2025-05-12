package com.mohanjp.binarySearchProblems;

import java.util.Arrays;

/**
 * Leet code -> @link https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] arr = { 5, 7, 7, 8, 8, 10 };
        int target = 7;

        System.out.println("The target element of " + target + " indices is : " + Arrays.toString(searchRange(arr, target)));
    }
    public static int[] searchRange(int[] nums, int target) {
        int[] positions = { -1, -1 };
        positions[0] = binarySearch(nums, target, true);

        // check for first occurrence if target first, if -1 means there is no target element.
        if(positions[0] != -1) {
            positions[1] = binarySearch(nums, target, false);
        }

        return positions;
    }

    private static int binarySearch(int[] nums, int target, boolean isStartPosition) {
        int start = 0;
        int end = nums.length - 1;
        int curPosition = -1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(nums[mid] == target) {
                curPosition = mid;

                // position found but have to check previous index or next index
                if(isStartPosition)
                    end = mid - 1;
                else
                    start = mid + 1;

            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return curPosition;
    }
}
