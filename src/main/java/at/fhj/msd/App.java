package at.fhj.msd;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 * Main class to evaluate and compare search algorithms.
 */
public class App {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        String[] methods = {"Linear", "Binary", "Interpolation", "QuadraticBinary"};

        try (PrintWriter writer = new PrintWriter(new FileWriter("evaluation/statistik.txt"))) {
            for (int size : sizes) {
                int[] array = generateSortedArray(size, size);
                writer.println("===== Array Size: " + size + " =====");

                for (String method : methods) {
                    long minTime = Long.MAX_VALUE;
                    long maxTime = Long.MIN_VALUE;
                    long totalTime = 0;

                    // Search for all values from 1 to size
                    for (int target = 1; target <= size; target++) {
                        long start = System.nanoTime();

                        switch (method) {
                            case "Linear":
                                SearchAlgorithms.linearSearch(array, target);
                                break;
                            case "Binary":
                                SearchAlgorithms.binarySearch(array, target);
                                break;
                            case "Interpolation":
                                SearchAlgorithms.interpolationSearch(array, target);
                                break;
                            case "QuadraticBinary":
                                SearchAlgorithms.quadraticBinarySearch(array, target);
                                break;
                        }

                        long duration = System.nanoTime() - start;
                        totalTime += duration;
                        if (duration < minTime) minTime = duration;
                        if (duration > maxTime) maxTime = duration;
                    }

                    // Convert time from nanoseconds to milliseconds
                    double avgTimeMs = totalTime / (double) size / 1_000_000.0;
                    double minTimeMs = minTime / 1_000_000.0;
                    double maxTimeMs = maxTime / 1_000_000.0;

                    writer.printf("%s Search -> Min: %.3f ms | Max: %.3f ms | Average: %.3f ms%n",
                            method, minTimeMs, maxTimeMs, avgTimeMs);
                }

                writer.println();
            }
            System.out.println("Results saved in evaluation/statistik.txt");

        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
        }
    }

    /**
     * This method creates a sorted array with unique values from 1 to maxValue.
     * First, the values are randomly shuffled, then sorted.
     * This simulates realistic data input.
     *
     * @param size Number of elements in the array.
     * @param maxValue Maximum possible value (should be >= size).
     * @return A sorted integer array.
     */
    public static int[] generateSortedArray(int size, int maxValue) {
        if (size > maxValue || size <= 0)
            throw new IllegalArgumentException("Invalid size or maxValue");

        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1; // Fill array with values from 1 to size
        }

        Collections.shuffle(Arrays.asList(array)); // Random order

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }

        Arrays.sort(result); // Sort the array for searching
        return result;
    }
}
