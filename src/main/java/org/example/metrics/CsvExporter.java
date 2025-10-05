package org.example.metrics;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class CsvExporter {

    private static final String HEADER =
            "Algorithm,DataSize,Comparisons,Swaps,ArrayAccesses,MemoryAllocations,RecursiveCalls,MaxDepth,RuntimeNanos";


    public static void write(List<MetricRecord> records, Path outputPath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write(HEADER);
            writer.newLine();
            for (MetricRecord record : records) {
                writer.write(String.format(
                        "%s,%d,%d,%d,%d,%d,%d,%d,%d",
                        record.algorithmName(),
                        record.dataSize(),
                        record.comparisons(),
                        record.swaps(),
                        record.arrayAccesses(),
                        record.memoryAllocations(),
                        record.recursiveCalls(),
                        record.maxDepth(),
                        record.runtimeNanos()
                ));
                writer.newLine();
            }
        }
    }
}
