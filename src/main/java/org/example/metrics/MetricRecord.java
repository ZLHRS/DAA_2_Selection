package org.example.metrics;

public record MetricRecord(
        String algorithmName,
        int dataSize,
        long comparisons,
        long swaps,
        long arrayAccesses,
        long memoryAllocations,
        long recursiveCalls,
        long maxDepth,
        long runtimeNanos
) {}
