package binarySearchProblems;

/**
 * Leet code -> @link https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 */
public class NextGreatestLetter {

    public static void main(String[] args) {
        char[] arr = { 'c', 'c', 'f', 'y' };
        char target = 'c';

        System.out.println("The target element of " + target + " ceiling is : " + nextGreatestLetter(arr, target));
    }
    public static char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(letters[mid] > target) {
                end = mid - 1;
            } else { // letters[mid] <= target
                start = mid + 1;
            }
        }

        return letters[start % letters.length];
    }
}
