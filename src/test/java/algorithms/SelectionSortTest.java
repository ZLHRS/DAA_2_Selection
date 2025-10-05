package algorithms;
import org.example.metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import org.example.algorithms.SelectionSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    private void assertSorted(int[] input) {
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);

        PerformanceTracker tracker = new PerformanceTracker();
        SelectionSort.sort(input, tracker);

        assertArrayEquals(expected, input);
        assertTrue(tracker.getComparisons() >= 0);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        SelectionSort.sort(arr, new PerformanceTracker());
        assertEquals(0, arr.length);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        SelectionSort.sort(arr, new PerformanceTracker());
        assertArrayEquals(new int[]{42}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker();
        SelectionSort.sort(arr, tracker);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(tracker.getComparisons() > 0);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        assertSorted(arr);
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {3, 1, 2, 1, 3, 2};
        assertSorted(arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = {8, 4, 6, 2, 9, 1};
        assertSorted(arr);
    }

    @Test
    void testNullArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> SelectionSort.sort(null, new PerformanceTracker()));
    }
}
