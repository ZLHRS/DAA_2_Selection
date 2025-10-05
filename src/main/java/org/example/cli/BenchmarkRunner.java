package org.example.cli;

import org.example.algorithms.SelectionSort;
import org.example.metrics.PerformanceTracker;
import org.example.util.ArrayGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Simple CLI to run benchmarks for SelectionSort and export metrics to CSV.
 */
public final class BenchmarkRunner {

    private BenchmarkRunner() {
        // utility class
    }

    /**
     * CLI entry point.
     *
     * Supported args:
     * --algorithm=SelectionSort    (only SelectionSort supported)
     * --n=1000                     (single size)
     * --trials=3
     * --dist=random|sorted|reversed|near_sorted
     * --out=metrics.csv
     */
    public static void main(final String[] args) {
        final Map<String, String> params = parseArgs(args);

        final String algorithm = params.getOrDefault("algorithm",
                "SelectionSort");
        final String dist = params.getOrDefault("dist", "random");
        final String output = params.getOrDefault("out", "metrics.csv");
        final int trials = Integer.parseInt(
                params.getOrDefault("trials", "3"));

        final List<Integer> sizes = new ArrayList<>();
        if (params.containsKey("n")) {
            sizes.add(Integer.parseInt(params.get("n")));
        } else {
            sizes.add(100);
            sizes.add(1000);
            sizes.add(10_000);
        }

        if (!"SelectionSort".equals(algorithm)) {
            System.err.println("Warning: only SelectionSort is supported. "
                    + "Falling back to SelectionSort.");
        }

        System.out.printf("Running SelectionSort benchmarks (%s)...%n", dist);

        try (FileWriter writer = new FileWriter(output)) {
            writer.write(
                    "algorithm,n,trial,comparisons,swaps,elapsed_ms\n");

            for (final int size : sizes) {
                for (int t = 1; t <= trials; t++) {
                    final int[] data = ArrayGenerator.generate(size, dist);
                    final PerformanceTracker tracker = new PerformanceTracker();

                    // SelectionSort.sort will start/stop timer inside
                    SelectionSort.sort(data, tracker);

                    writer.write(String.format("%s,%d,%d,%d,%d,%d%n",
                            "SelectionSort",
                            size,
                            t,
                            tracker.getComparisons(),
                            tracker.getSwaps(),
                            tracker.getElapsedTimeMillis()));

                    System.out.printf("n=%d trial=%d -> time=%dms, comp=%d, swaps=%d%n",
                            size,
                            t,
                            tracker.getElapsedTimeMillis(),
                            tracker.getComparisons(),
                            tracker.getSwaps());
                }
            }

            System.out.printf("%nResults saved to %s%n", Path.of(output)
                    .toAbsolutePath());
        } catch (final IOException ex) {
            System.err.println("Error writing CSV: " + ex.getMessage());
        }
    }

    private static Map<String, String> parseArgs(final String[] args) {
        final java.util.Map<String, String> map = new java.util.HashMap<>();
        for (final String arg : args) {
            if (arg != null && arg.startsWith("--") && arg.contains("=")) {
                final String[] parts = arg.substring(2).split("=", 2);
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        }
        return map;
    }
}
