package at.fhj.msd;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Main class to demonstrate search algorithms on sorted arrays.
 */
public class App {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        Random rand = new Random();
        try {
            for (int size : sizes) {
                int[] array = generateSortedArray(size, size);
                System.out.println("Array size: " + size);
                // Test 5 random elements for each size
                for (int i = 0; i < 5; i++) {
                    int target = rand.nextInt(size) + 1;
                    System.out.println("Target: " + target);
                    System.out.println("Linear Search Index: " + SearchAlgorithms.linearSearch(array, target));
                    System.out.println("Binary Search Index: " + SearchAlgorithms.binarySearch(array, target));
                    System.out.println("Interpolation Search Index: " + SearchAlgorithms.interpolationSearch(array, target));
                    System.out.println("Quadratic Binary Search Index: " + SearchAlgorithms.quadraticBinarySearch(array, target));
                }
            }
        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
        }
    }

    /**
     * Generates a sorted array of unique integers from 1 to maxValue.
     * @param size Size of the array.
     * @param maxValue Maximum value for elements (inclusive).
     * @return Sorted integer array.
     */
    public static int[] generateSortedArray(int size, int maxValue) {
        if (size > maxValue || size <= 0) throw new IllegalArgumentException("Invalid size or maxValue");
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1; // Fill with 1 to size
        }
        Collections.shuffle(Arrays.asList(array)); // Randomize order
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        Arrays.sort(result); // Ensure sorted for search algorithms
        return result;
    }
}