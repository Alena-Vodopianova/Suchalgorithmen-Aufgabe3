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
 * Performs quadratic binary search using interpolation and square-root stepping.
 * First estimates the likely position with interpolation, then jumps in sqrt(n) steps
 * to find the subrange, and finally uses binary search in that subrange.
 *
 * @param a Sorted integer array to search in.
 * @param x Target value to find.
 * @return Index of x if found, -1 otherwise.
 */
public static int quadraticBinarySearch(int[] a, int x) {
    if (a == null || a.length == 0) return -1;

    int n = a.length;
    int low = 0, high = n - 1;

    // While value is within search range
    while (low <= high && x >= a[low] && x <= a[high]) {
        // Avoid division by zero
        if (a[high] == a[low]) {
            if (a[low] == x) return low;
            else return -1;
        }

        // Step 1: Estimate position using interpolation formula
        int t = low + ((x - a[low]) * (high - low)) / (a[high] - a[low]);

        if (t < 0 || t >= n) return -1;

        if (a[t] == x) return t;

        // Step 2: Jump in sqrt(n) steps to find search subrange
        int step = (int) Math.sqrt(n);
        int i = t;

        if (a[t] < x) {
            while (i < n && a[i] < x) {
                i += step;
            }
            low = Math.max(t, i - step);
            high = Math.min(i, n - 1);
        } else {
            while (i >= 0 && a[i] > x) {
                i -= step;
            }
            high = Math.min(t, i + step);
            low = Math.max(i, 0);
        }
    }

    // Final binary search in narrowed subrange
    while (low <= high) {
        int mid = (low + high) / 2;
        if (a[mid] == x) return mid;
        else if (a[mid] < x) low = mid + 1;
        else high = mid - 1;
    }

    return -1;
}
