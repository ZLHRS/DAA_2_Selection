package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for exporting performance metrics into CSV files.
 */
public final class CsvExporter {

    /** Private constructor to prevent instantiation. */
    private CsvExporter() {
        // Utility class — no instances
    }

    /**
     * Writes a list of MetricRecord objects to a CSV file.
     *
     * @param records list of metric records
     * @param path    output file path
     * @throws IOException if writing fails
     */
    public static void write(final List<MetricRecord> records,
                             final Path path) throws IOException {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write("name,comparisons,swaps,arrayAccesses,"
                    + "memoryAllocations,recursiveCalls,runtimeNanos\n");
            for (MetricRecord r : records) {
                writer.write(String.format("%s,%d,%d,%d,%d,%d,%d%n",
                        r.getName(),
                        r.getComparisons(),
                        r.getSwaps(),
                        r.getArrayAccesses(),
                        r.getMemoryAllocations(),
                        r.getRecursiveCalls(),
                        r.getRuntimeNanos()));
            }
        }
    }
}
