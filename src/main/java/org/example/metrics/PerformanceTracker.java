package org.example.metrics;

/**
 * Tracks performance metrics during algorithm execution.
 * <p>This includes counts of comparisons, swaps, array accesses,
 * recursion depth, memory allocations (approximate),
 * and runtime measurement in nanoseconds.</p>
 */
public final class PerformanceTracker {

    /** Number of element comparisons. */
    private long comparisons;

    /** Number of element swaps. */
    private long swaps;

    /** Number of array element accesses. */
    private long arrayAccesses;

    /** Approximate number of memory allocations. */
    private long memoryAllocations;

    /** Number of recursive calls. */
    private long recursiveCalls;

    /** Start time in nanoseconds. */
    private long startTime;

    /** End time in nanoseconds. */
    private long endTime;

    /** Current recursion depth. */
    private int currentDepth;

    /** Maximum recursion depth reached. */
    private int maxDepth;

    /** Creates a new instance of PerformanceTracker. */
    public PerformanceTracker() {
        this.comparisons = 0L;
        this.swaps = 0L;
        this.arrayAccesses = 0L;
        this.memoryAllocations = 0L;
        this.recursiveCalls = 0L;
        this.startTime = 0L;
        this.endTime = 0L;
        this.currentDepth = 0;
        this.maxDepth = 0;
    }

    /** Starts the timer. */
    public void startTimer() {
        startTime = System.nanoTime();
    }

    /** Stops the timer. */
    public void stopTimer() {
        endTime = System.nanoTime();
    }

    /**
     * Gets the runtime in nanoseconds.
     *
     * @return runtime in nanoseconds
     */
    public long getRuntimeNanos() {
        return endTime - startTime;
    }

    /** Increments comparison counter. */
    public void incComparisons() {
        comparisons++;
    }

    /** Increments swap counter. */
    public void incSwaps() {
        swaps++;
    }

    /** Increments array access counter. */
    public void incArrayAccess() {
        arrayAccesses++;
    }

    /** Increments memory allocation counter. */
    public void incMemoryAllocations() {
        memoryAllocations++;
    }

    /** Increments recursion depth counter. */
    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
        recursiveCalls++;
    }

    /** Decrements recursion depth counter. */
    public void exitRecursion() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    /**
     * @return number of comparisons
     */
    public long getComparisons() {
        return comparisons;
    }

    /**
     * @return number of swaps
     */
    public long getSwaps() {
        return swaps;
    }

    /**
     * @return number of array accesses
     */
    public long getArrayAccesses() {
        return arrayAccesses;
    }

    /**
     * @return number of memory allocations
     */
    public long getMemoryAllocations() {
        return memoryAllocations;
    }

    /**
     * @return number of recursive calls
     */
    public long getRecursiveCalls() {
        return recursiveCalls;
    }

    /**
     * @return maximum recursion depth
     */
    public int getMaxDepth() {
        return maxDepth;
    }
}
