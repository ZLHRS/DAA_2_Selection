package org.example.util;

import java.util.Random;

/**
 * Small helper to create arrays with different distributions.
 */
public final class ArrayGenerator {

    private static final Random RAND = new Random(42);

    private ArrayGenerator() {
        // utility
    }

    /**
     * Generate array of length n with distribution type.
     *
     * @param n    desired length
     * @param type random|sorted|reversed|near_sorted
     * @return generated array
     */
    public static int[] generate(final int n, final String type) {
        final int[] arr = new int[Math.max(0, n)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RAND.nextInt(Math.max(1, n * 2));
        }

        switch (type) {
            case "sorted":
                java.util.Arrays.sort(arr);
                break;
            case "reversed":
                java.util.Arrays.sort(arr);
                reverse(arr);
                break;
            case "near_sorted":
                java.util.Arrays.sort(arr);
                shuffle(arr, Math.max(1, arr.length / 20));
                break;
            default:
                // random
                break;
        }
        return arr;
    }

    private static void reverse(final int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            final int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    private static void shuffle(final int[] arr, final int swaps) {
        for (int k = 0; k < swaps; k++) {
            final int a = RAND.nextInt(arr.length);
            final int b = RAND.nextInt(arr.length);
            final int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
    }
}
