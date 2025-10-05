package org.example.algorithms;

import org.example.metrics.PerformanceTracker;

/**
 * Implements the Selection Sort algorithm.
 * <p>
 * This class provides a static method to sort an array in ascending order
 * while optionally tracking performance metrics such as comparisons, swaps,
 * and execution time.
 * </p>
 */
public final class SelectionSort {

    private SelectionSort() {
    }

    /**
     * Sorts the given array in ascending order
     * using the selection sort algorithm.
     *
     * @param a       the array to be sorted
     * @param tracker metrics tracker for performance measurement
     * @throws IllegalArgumentException if the array is null
     */
    public static void sort(final int[] a, final PerformanceTracker tracker) {
        if (a == null) {
            throw new IllegalArgumentException("array must not be null");
        }

        final PerformanceTracker perf =
                tracker != null ? tracker : new PerformanceTracker();
        perf.startTimer();

        final int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                perf.incComparisons();
                perf.incArrayAccess();

                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
                perf.incSwaps();
            }
        }

        perf.stopTimer();
    }
}
