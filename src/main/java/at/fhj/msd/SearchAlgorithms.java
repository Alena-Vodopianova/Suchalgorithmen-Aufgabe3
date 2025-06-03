package at.fhj.msd;

/**
 * Provides implementations of various search algorithms for sorted integer arrays.
 */
public class SearchAlgorithms {

    /**
     * Performs linear search to find the index of the target value in the array.
     * @param a Sorted integer array to search in.
     * @param x Target value to find.
     * @return Index of x if found, -1 otherwise.
     */
    public static int linearSearch(int[] a, int x) {
        if (a == null || a.length == 0) {
            return -1;
    }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    /**
     * Performs binary search to find the index of the target value in the array.
     * @param a Sorted integer array to search in.
     * @param x Target value to find.
     * @return Index of x if found, -1 otherwise.
     */
    public static int binarySearch(int[] a, int x) {
        if (a == null || a.length == 0) {
            return -1;
             }
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoids integer overflow
            if (a[mid] == x){
                return mid;
                }
            if (a[mid] < x){
                 low = mid + 1;
                 }
            else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Performs interpolation search to find the index of the target value in the array.
     * @param a Sorted integer array to search in.
     * @param x Target value to find.
     * @return Index of x if found, -1 otherwise.
     */
    public static int interpolationSearch(int[] a, int x) {
        if (a == null || a.length == 0) {
            return -1;
         }
        int low = 0, high = a.length - 1;
        while (low <= high && x >= a[low] && x <= a[high]) {
            if (low == high) {
                if (a[low] == x) {
                    return low;
                     }
                return -1;
            }
            int pos = low + ((x - a[low]) * (high - low)) / (a[high] - a[low]);
            if (pos < 0 || pos >= a.length){
                return -1;
                 }
            if (a[pos] == x) {
                return pos;
             }
            if (a[pos] < x) {
                low = pos + 1;
                 }
            else {
                high = pos - 1;
             }
        }
        return -1;
    }

    /**
     * Performs quadratic binary search by dividing the array into three parts.
     * @param a Sorted integer array to search in.
     * @param x Target value to find.
     * @return Index of x if found, -1 otherwise.
     */
    public static int quadraticBinarySearch(int[] a, int x) {
        if (a == null || a.length == 0) { 
            return -1;
            }
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int third = (high - low) / 3;
            int mid1 = low + third;
            int mid2 = high - third;
            if (mid1 >= a.length || mid2 >= a.length) { 
                return -1;
            }
            if (a[mid1] == x) {
                    return mid1;
            }
            if (a[mid2] == x) {
                return mid2;
            }
            if (x < a[mid1]) {
                high = mid1 - 1;
                }
            else if (x > a[mid2]) {
                low = mid2 + 1;
                }
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }
}
