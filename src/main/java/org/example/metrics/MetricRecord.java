package org.example.metrics;

/**
 * Represents a single measurement record
 * of performance metrics for an algorithm.
 */
public final class MetricRecord {

    /** Algorithm name. */
    private final String name;

    /** Number of comparisons. */
    private final long comparisons;

    /** Number of swaps. */
    private final long swaps;

    /** Number of array accesses. */
    private final long arrayAccesses;

    /** Number of memory allocations. */
    private final long memoryAllocations;

    /** Number of recursive calls. */
    private final long recursiveCalls;

    /** Execution time in nanoseconds. */
    private final long runtimeNanos;

    /**
     * Constructs a new immutable metric record.
     *
     * @param algoName          algorithm name
     * @param numComparisons    number of comparisons
     * @param numSwaps          number of swaps
     * @param numArrayAccesses  number of array accesses
     * @param numAllocations    number of memory allocations
     * @param numRecursiveCalls number of recursive calls
     * @param execTimeNanos     execution time in nanoseconds
     */
    public MetricRecord(final String algoName,
                        final long numComparisons,
                        final long numSwaps,
                        final long numArrayAccesses,
                        final long numAllocations,
                        final long numRecursiveCalls,
                        final long execTimeNanos) {
        this.name = algoName;
        this.comparisons = numComparisons;
        this.swaps = numSwaps;
        this.arrayAccesses = numArrayAccesses;
        this.memoryAllocations = numAllocations;
        this.recursiveCalls = numRecursiveCalls;
        this.runtimeNanos = execTimeNanos;
    }

    /** @return algorithm name */
    public String getName() {
        return name;
    }

    /** @return number of comparisons */
    public long getComparisons() {
        return comparisons;
    }

    /** @return number of swaps */
    public long getSwaps() {
        return swaps;
    }

    /** @return number of array accesses */
    public long getArrayAccesses() {
        return arrayAccesses;
    }

    /** @return number of memory allocations */
    public long getMemoryAllocations() {
        return memoryAllocations;
    }

    /** @return number of recursive calls */
    public long getRecursiveCalls() {
        return recursiveCalls;
    }

    /** @return runtime in nanoseconds */
    public long getRuntimeNanos() {
        return runtimeNanos;
    }
}
