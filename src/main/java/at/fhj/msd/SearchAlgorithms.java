package at.fhj.msd;

/**
 * Provides implementations of various search algorithms for sorted integer arrays.
 */
public class SearchAlgorithms {

    /**
     * Performs linear search to find the index of the target value in the array.
     * Checks each value one by one from left to right.
     */
    public static int linearSearch(int[] a, int x) {
        if (a == null || a.length == 0) return -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    /**
     * Performs binary search on a sorted array.
     * The method splits the array in half each time.
     */
    public static int binarySearch(int[] a, int x) {
        if (a == null || a.length == 0) return -1;
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // safe way to calculate middle
            if (a[mid] == x) return mid;
            if (a[mid] < x) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    /**
     * Performs interpolation search on a sorted array.
     * Works best when values are evenly distributed.
     */
    public static int interpolationSearch(int[] a, int x) {
        if (a == null || a.length == 0) return -1;
        int low = 0, high = a.length - 1;
        while (low <= high && x >= a[low] && x <= a[high]) {
            if (low == high) {
                if (a[low] == x) return low;
                return -1;
            }
            int pos = low + ((x - a[low]) * (high - low)) / (a[high] - a[low]);
            if (pos < 0 || pos >= a.length) return -1;
            if (a[pos] == x) return pos;
            if (a[pos] < x) low = pos + 1;
            else high = pos - 1;
        }
        return -1;
    }

    /**
     * Performs quadratic binary search by using interpolation to guess the position,
     * then searches in steps of sqrt(n), and finally uses binary search in the range.
     */
    public static int quadraticBinarySearch(int[] a, int x) {
        if (a == null || a.length == 0) return -1;

        int n = a.length;
        int low = 0, high = n - 1;

        while (low <= high && x >= a[low] && x <= a[high]) {
            // Avoid division by zero
            if (a[high] == a[low]) {
                if (a[low] == x) return low;
                else return -1;
            }

            // Estimate position
            int t = low + ((x - a[low]) * (high - low)) / (a[high] - a[low]);
            if (t < 0 || t >= n) break;

            if (a[t] == x) return t;

            // Step with sqrt(n)
            int step = (int) Math.sqrt(n);
            int i = t;

            if (a[t] < x) {
                while (i < high && a[i] < x) {
                    i += step;
                }
                low = Math.max(t + 1, i - step);
                high = Math.min(i, high);
            } else {
                while (i > low && a[i] > x) {
                    i -= step;
                }
                high = Math.min(t - 1, i + step);
                low = Math.max(i, low);
            }
        }

        // Final binary search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == x) return mid;
            if (a[mid] < x) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }
}
