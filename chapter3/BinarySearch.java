package chapter3;

public class BinarySearch {

    static int binarySearch(int[] values, int target) {
        /*
         * Helper method to initially call the actual binarySearch method with
         * the first and last indexes.
         */
        return binarySearch(values, target, 0, values.length - 1);
    }

    static int binarySearch(int[] values, int target, int first, int last) {
        /* binarySearch recursive method. */

        int mid = (first + last) / 2;
        // Calculating midpoint based on given indexes.

        if (first > last) {
            // Base case: If first > last it means that we have exhausted
            // the entire array without finding the target element.
            // Return -1 to indicate that the element is not in the array!
            // System.out.println("Called me!");
            return -1;
        }

        else if (target == values[mid]) {
            // Base case: If the value at values[mid] is equal to the target,
            // then we found our target at the mid index. Return the value of
            // mid which is the position we found the target at.
            System.out.println("Called me!");
            return mid;
        }

        else if (target > values[mid]) {
            // Recursive case: If our target value is greater than the value
            // at the mid index, then we continue to search through the right
            // half of the array. Which is from the range of indexes mid+1 to
            // the last index. In other words our new call works on the smaller,
            // right subarray with the frst parameter set to mid+1 and the last
            // parameter staying the same.
            System.out.println("Processing Recursive call...");
            return binarySearch(values, target, mid + 1, last);
        }

        else {
            // Recursive case: If our target value is less than the value
            // at the mid index, then we continue to search through the left
            // half of the array. Which is from the range of indexes first to
            // the mid-1 th index. In other words our new call works on the smaller,
            // left subarray with the frst parameter set to our current first and
            // the last parameter changing to mid-1.
            System.out.println("Processing Recursive call...");
            return binarySearch(values, target, first, mid - 1);
        }

    }

    public static void main(String[] args) {
        int[] myArray = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        int target = 8;
        int result = binarySearch(myArray, target);

        if (result != -1) {
            System.out.println("The target value: " + target + " was found at index: " + result);
        } else {
            System.out.println("The target value: " + target + " was not found in the array!");
        }

    }
}
