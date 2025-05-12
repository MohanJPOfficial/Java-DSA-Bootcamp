package arrayProblems;

import java.util.*;

/**
 * Tuf -> @link https://takeuforward.org/plus/dsa/arrays/logic-building/intersection-of-two-sorted-arrays
 * <p>
 * Input: nums1 = [1, 2, 2, 3, 5], nums2 = [1, 2, 7]
 * Output: [1, 2]
 * Explanation: The elements 1, 2 are the only elements present in both nums1 and nums2
 */
public class IntersectionArrays {

    public static void main(String[] args) {

        int[] nums1 = { 1, 2, 2, 3, 5 };
        int[] nums2 = { 1, 2, 7 };

        System.out.print("The intersection array of " + Arrays.toString(nums1) + Arrays.toString(nums2) + "  is : " + Arrays.toString(intersectionArrayOptimal(nums1, nums2)));
    }

    /**
     * Brute force approach -> linear search
     * <p>
     * Time Complexity: O(MxN), where M is the length of nums1 and N is the length of nums2.
     *
     * Space Complexity: O(N), where N is size of nums2, extra space to store answer is not considered.
     */
    private static int[] intersectionArrayBruteForce(int[] nums1, int[] nums2) {

        List<Integer> intersectionList = new ArrayList<>();
        int[] visited = new int[nums2.length];


        for (int i = 0; i < nums1.length; i++) {

            for (int j = 0; j < nums2.length; j++) {

                /**
                 * If nums1[i] is equal to nums2[j] and nums2[j]
                 * is not visited then add nums1[i] in ans.
                 */
                if (nums1[i] == nums2[j] && visited[j] != 1) {

                    // Mark as visited
                    visited[j] = 1;
                    intersectionList.add(nums1[i]);

                    break;
                }
                // If nums2[j] is greater than nums1[i], break out of loop
                else if (nums1[i] < nums2[j]) {
                    break;


                }
            }
        }

        // list to array
        return intersectionList.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Optimal approach -> Use two pointers approach
     *
     * Time Complexity:O(m + n) where m and n are the lengths of nums1 and nums2 respectively,
     * due to the while loop and O(k) for converting the list to array where k is the size of the intersection.
     *
     * Space Complexity:O(min(m, n)) in the worst case, to store the intersection elements in intersectionList, and O(k) to store the result in an array,
     * where k is the size of the intersection.
     */
    private static int[] intersectionArrayOptimal(int[] nums1, int[] nums2) {

        List<Integer> intersectionList = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        // Traverse both arrays using two pointers approach
        while(leftIndex < nums1.length && rightIndex < nums2.length) {
            if(nums1[leftIndex] == nums2[rightIndex]) {

                intersectionList.add(nums1[leftIndex]);
                leftIndex++;
                rightIndex++;

            } else if(nums1[leftIndex] < nums2[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex++;
            }
        }

        return intersectionList.stream().mapToInt(Integer::intValue).toArray();
    }
}
