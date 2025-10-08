import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MetricsRunner {

    // Utility: write one line of CSV
    private static void writeCSV(String algo, int n, double timeMs, int depth) {
        try (FileWriter fw = new FileWriter("metrics.csv", true)) {
            fw.write(String.format("%s,%d,%.4f,%d\n", algo, n, timeMs, depth));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Measure MergeSort
    private static void benchmarkMergeSort() {
        System.out.println("▶ Measuring MergeSort...");
        for (int n : new int[]{1000, 5000, 10000, 50000, 100000}) {
            int[] arr = new Random().ints(n, 0, 1_000_000).toArray();
            long start = System.nanoTime();
            MergeSortDemo.mergeSort(arr);
            long end = System.nanoTime();
            double timeMs = (end - start) / 1e6;
            int depth = (int) (Math.log(n) / Math.log(2)); // approx depth
            writeCSV("mergesort", n, timeMs, depth);
        }
    }

    // Measure QuickSort
    private static void benchmarkQuickSort() {
        System.out.println("▶ Measuring QuickSort...");
        for (int n : new int[]{1000, 5000, 10000, 50000, 100000}) {
            int[] arr = new Random().ints(n, 0, 1_000_000).toArray();
            long start = System.nanoTime();
            QuickSortDemo.quickSort(arr, 0, arr.length - 1);
            long end = System.nanoTime();
            double timeMs = (end - start) / 1e6;
            int depth = (int) (2 * Math.log(n) / Math.log(2)); // typical upper bound
            writeCSV("quicksort", n, timeMs, depth);
        }
    }

    // Measure Deterministic Select (Median-of-Medians)
    private static void benchmarkSelect() {
        System.out.println("▶ Measuring Deterministic Select...");
        for (int n : new int[]{1000, 5000, 10000, 50000, 100000}) {
            int[] arr = new Random().ints(n, 0, 1_000_000).toArray();
            int k = n / 2;
            long start = System.nanoTime();
            SelectKthDemo.selectKth(arr, k);
            long end = System.nanoTime();
            double timeMs = (end - start) / 1e6;
            int depth = (int) (Math.log(n) / Math.log(5)); // rough bound
            writeCSV("select", n, timeMs, depth);
        }
    }

    // Measure Closest Pair (O(n log n) version)
    private static void benchmarkClosestPair() {
        System.out.println("▶ Measuring Closest Pair...");
        for (int n : new int[]{100, 500, 1000, 5000, 10000}) {
            ClosestPairDemo.Point[] pts = new ClosestPairDemo.Point[n];
            Random r = new Random();
            for (int i = 0; i < n; i++) {
                pts[i] = new ClosestPairDemo.Point(r.nextDouble() * 10000, r.nextDouble() * 10000);
            }
            long start = System.nanoTime();
            ClosestPairDemo.closestPair(pts);
            long end = System.nanoTime();
            double timeMs = (end - start) / 1e6;
            int depth = (int) (Math.log(n) / Math.log(2));
            writeCSV("closestpair", n, timeMs, depth);
        }
    }

    public static void main(String[] args) {
        // clear previous file
        try (FileWriter fw = new FileWriter("metrics.csv")) {
            fw.write("algorithm,n,time_ms,depth\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        benchmarkMergeSort();
        benchmarkQuickSort();
        benchmarkSelect();
        benchmarkClosestPair();

        System.out.println("✅ Metrics collection complete → see metrics.csv");
    }
}
