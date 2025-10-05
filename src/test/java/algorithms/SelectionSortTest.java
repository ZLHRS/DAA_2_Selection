package algorithms;

import org.example.algorithms.SelectionSort;
import org.example.metrics.PerformanceTracker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    private final Random random = new Random(42);

    @Test
    @DisplayName("Sorts correctly on small fixed arrays")
    void testSmallFixedArrays() {
        int[][] testCases = {
                {},
                {1},
                {2, 1},
                {3, 1, 2},
                {5, -1, 0, 10, 3}
        };

        for (int[] arr : testCases) {
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);

            int[] actual = Arrays.copyOf(arr, arr.length);
            SelectionSort.sort(actual, new PerformanceTracker());

            assertArrayEquals(expected, actual, "Failed for: " + Arrays.toString(arr));
        }
    }

    @Test
    @DisplayName("Sorts 1000 random arrays correctly compared to Arrays.sort()")
    void testRandomArrays() {
        for (int i = 0; i < 1000; i++) {
            int n = random.nextInt(200) + 1; // размер 1..200
            int[] arr = random.ints(n, -1000, 1000).toArray();
            int[] expected = Arrays.copyOf(arr, n);
            Arrays.sort(expected);

            int[] actual = Arrays.copyOf(arr, n);
            SelectionSort.sort(actual, null);

            assertArrayEquals(expected, actual, "Mismatch at trial " + i);
        }
    }

    @Test
    @DisplayName("Handles sorted, reverse-sorted and nearly-sorted arrays")
    void testDistributions() {
        int n = 100;
        int[] sorted = new int[n];
        int[] reversed = new int[n];
        int[] nearlySorted = new int[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = i;
            reversed[i] = n - i;
            nearlySorted[i] = i;
        }
        // Перемешиваем немного
        for (int i = 0; i < 5; i++) {
            int i1 = random.nextInt(n);
            int i2 = random.nextInt(n);
            int tmp = nearlySorted[i1];
            nearlySorted[i1] = nearlySorted[i2];
            nearlySorted[i2] = tmp;
        }

        checkDistribution(sorted);
        checkDistribution(reversed);
        checkDistribution(nearlySorted);
    }

    private void checkDistribution(int[] arr) {
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        int[] actual = Arrays.copyOf(arr, arr.length);
        SelectionSort.sort(actual, new PerformanceTracker());
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("PerformanceTracker metrics behave correctly on simple cases")
    void testMetrics() {
        PerformanceTracker tracker = new PerformanceTracker();
        int[] arr = {3, 2, 1};

        SelectionSort.sort(arr, tracker);

        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);
        assertTrue(tracker.getElapsedTimeMillis() >= 0);
    }
}
