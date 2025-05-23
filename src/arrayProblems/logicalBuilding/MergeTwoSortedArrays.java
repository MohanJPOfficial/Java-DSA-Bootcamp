package arrayProblems.logicalBuilding;

import java.util.*;

/**
 * Tuf -> @link https://takeuforward.org/plus/dsa/arrays/logic-building/union-of-two-sorted-arrays
 *
 * Input: nums1 = [1, 2, 3, 4, 5], nums2 = [1, 2, 7]
 * Output: [1, 2, 3, 4, 5, 7]
 *
 * Explanation: The elements 1, 2 are common to both, 3, 4, 5 are from nums1 and 7 is from nums2
 */
public class MergeTwoSortedArrays {

    public static void main(String[] args) {

        int[] nums1 = { 1, 2, 3, 4, 5 };
        int[] nums2 = { 1, 2, 7 };

        System.out.print("The union of two sorted array " + Arrays.toString(nums1) + Arrays.toString(nums2) + "  is : " + Arrays.toString(unionArrayByOptimal(nums1, nums2)));
    }

    /**
     * Brute force approach -> Use tree set for sorted and non-duplicates
     *
     * O(n + m + k), where n is the size of nums1, m is the size of nums2, and k is the size of the union of nums1 and nums2.
     * Adding elements to the TreeSet takes O(log k) time each, but amortized over the loops, it is considered O(1) per element.
     * The final loop to create the array takes O(k) time.
     *
     * O(k), where k is the number of unique elements in the combined arrays nums1 and nums2, due to the TreeSet and the resulting array.
     */
    private static int[] unionArrayByBruteForce(int[] nums1, int[] nums2) {
        Set<Integer> union = new TreeSet<>();

        for(int i : nums1) {
            union.add(i);
        }

        for(int j : nums2) {
            union.add(j);
        }

        int[] arr = new int[union.size()];

        int index = 0;
        for(int k : union) {
            arr[index] = k;
            index++;
        }

        return arr;
    }

    /**
     * Optimal approach -> Use two pointers approach
     *
     * Time Complexity:O(m+n) where n is the length of nums1 and m is the length of nums2 due to the while loops.
     *
     * Space Complexity:O(m+n) in the worst case where all elements from both arrays are unique,
     * resulting in a unionList of size m+n. Additional O(m+n) for output array.
     */
    private static int[] unionArrayByOptimal(int[] nums1, int[] nums2) {

        int left = 0;
        int right = 0;

        List<Integer> unionList = new ArrayList<>();

        // Traverse both arrays until one is exhausted
        while (left < nums1.length && right < nums2.length) {

            // If nums1[left] is smaller or equal, consider adding it
            if (nums1[left] <= nums2[right]) {

                // Add only if it's not a duplicate of the last inserted element
                if (unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums1[left]) {
                    unionList.add(nums1[left]);
                }
                left++; // Move pointer in nums1

            } else {

                // nums2[right] is smaller, consider adding it
                if (unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums2[right]) {
                    unionList.add(nums2[right]);
                }
                right++; // Move pointer in nums2
            }
        }

        // Add remaining elements from nums1 (if any), avoiding duplicates
        while (left < nums1.length) {
            if (unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums1[left]) {
                unionList.add(nums1[left]);
            }
            left++;
        }

        // Add remaining elements from nums2 (if any), avoiding duplicates
        while (right < nums2.length) {
            if (unionList.isEmpty() || unionList.get(unionList.size() - 1) != nums2[right]) {
                unionList.add(nums2[right]);
            }
            right++;
        }

        // Convert the result list to an int[] array and return
        return listToArray(unionList);
    }

    private static int[] listToArray(List<Integer> unionList) {
        int[] union = new int[unionList.size()];

        int index = 0;

        for (int i : unionList) {
            union[index] = i;
            index++;
        }

        return union;
    }
}
