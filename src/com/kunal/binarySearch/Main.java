package com.kunal.binarySearch;

public class Main {
    public static void main(String[] args) {
        int[] arr = {3, 3, 7, 3};
        System.out.println(findPivotRBS(arr));
    }

    // In Bitonic Array: [1, 3, 8, 4, 3]
    public static int findPivot(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > arr[mid + 1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return arr[low];
    }

    // In rotated array: {3, 4, 5, 6, 1, 2}
    // NOTE: Will fail if array contains duplicates
    public static int findPivotRBS(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid < high && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (mid > low && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }
            if (arr[low] >= arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int pivot2(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid < high && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (mid > low && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }

            // this is the only difference from the previous solution
            // if numbers at indices start, mid, and end are same, we can't choose a side
            // the best we can do is to skip one number from both ends if they are not the smallest number
            if (arr[low] == arr[mid] && arr[high] == arr[mid]) {
                if (arr[low] > arr[low + 1]) // if element at start+1 is not the smallest
                    return low;
                ++low;
                if (arr[high - 1] > arr[high]) // if the element at end is not the smallest
                    return high - 1;
                --high;
                // left side is sorted, so the pivot is on right side
            } else if (arr[low] < arr[mid] || (arr[low] == arr[mid] && arr[mid] > arr[high])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’
    public static int searchCeilingOfANumber(int[] arr, int key) {
        if (key > arr[arr.length - 1]) // if the 'key' is bigger than the biggest element
            return -1;

        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else { // found the key
                return mid;
            }
        }
        // since the loop is running until 'low <= high', so at the high of the while loop, 'low == high+1'
        // we are not able to find the element in the given array, so the next big number will be arr[low]
        return low;
    }

    // floor of the ‘key’ will be the biggest element in the given array smaller than or equal to the ‘key’
    public static int floor(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == value) {
                return mid;
            }
            if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    // find the smallest element next greater than the given value, if none then return the first element
    public static int nextGreater(int[] arr, int value) {
        if (value > arr[arr.length - 1] || value < arr[0]) {
            return arr[0];
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // since the loop is running until 'low <= high', so at the end of the while loop, 'low == high+1'
        return arr[low % arr.length];
    }
}
