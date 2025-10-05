package org.example.benchmarks;
import org.example.algorithms.SelectionSort;
import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SelectionSortBenchmark {

    @Param({"100", "1000", "10000"})
    private int size;

    @Param({"random", "sorted", "reversed"})
    private String dist;

    private int[] array;

    @Setup(Level.Invocation)
    public void setUp() {
        array = generateArray(size, dist);
    }

    private int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        if (type.equals("random")) {
            Random r = new Random();
            for (int i = 0; i < n; i++) {
                int j = r.nextInt(n);
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        } else if (type.equals("reversed")) {
            for (int i = 0; i < n / 2; i++) {
                int tmp = arr[i];
                arr[i] = arr[n - 1 - i];
                arr[n - 1 - i] = tmp;
            }
        }
        return arr;
    }

    @Benchmark
    public void testSelectionSort() {
        int[] copy = Arrays.copyOf(array, array.length);
        SelectionSort.sort(copy, null);
    }
}
