package org.example.metrics;
import java.util.concurrent.atomic.AtomicLong;

public class PerformanceTracker {

    private final AtomicLong comparisons = new AtomicLong();
    private final AtomicLong swaps = new AtomicLong();
    private final AtomicLong arrayAccesses = new AtomicLong();
    private final AtomicLong memoryAllocations = new AtomicLong();
    private final AtomicLong recursiveCalls = new AtomicLong();

    private long currentDepth = 0;
    private long maxDepth = 0;

    private long startTimeNanos;
    private long runtimeNanos;


    public void startTimer() {
        startTimeNanos = System.nanoTime();
    }

    public void stopTimer() {
        runtimeNanos = System.nanoTime() - startTimeNanos;
    }

    public void incComparisons() {
        comparisons.incrementAndGet();
    }

    public void incSwaps() {
        swaps.incrementAndGet();
    }

    public void incArrayAccess() {
        arrayAccesses.incrementAndGet();
    }

    public void incMemoryAllocations() {
        memoryAllocations.incrementAndGet();
    }


    public void enterRecursion() {
        recursiveCalls.incrementAndGet();
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }


    public void exitRecursion() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    public long getComparisons() {
        return comparisons.get();
    }

    public long getSwaps() {
        return swaps.get();
    }

    public long getArrayAccesses() {
        return arrayAccesses.get();
    }

    public long getMemoryAllocations() {
        return memoryAllocations.get();
    }

    public long getRecursiveCalls() {
        return recursiveCalls.get();
    }

    public long getMaxDepth() {
        return maxDepth;
    }

    public long getRuntimeNanos() {
        return runtimeNanos;
    }

    public MetricRecord snapshot(String algorithmName, int dataSize) {
        return new MetricRecord(
                algorithmName,
                dataSize,
                getComparisons(),
                getSwaps(),
                getArrayAccesses(),
                getMemoryAllocations(),
                getRecursiveCalls(),
                getMaxDepth(),
                getRuntimeNanos()
        );
    }

    public void reset() {
        comparisons.set(0);
        swaps.set(0);
        arrayAccesses.set(0);
        memoryAllocations.set(0);
        recursiveCalls.set(0);
        currentDepth = 0;
        maxDepth = 0;
        runtimeNanos = 0;
    }
}
