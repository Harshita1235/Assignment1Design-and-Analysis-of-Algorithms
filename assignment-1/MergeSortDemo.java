public class MergeSortDemo {
    private static final int CUTOFF = 32;

    public static void mergeSort(int[] a) {
        int[] buf = new int[a.length];
        mergeSort(a, buf, 0, a.length);
    }

    private static void mergeSort(int[] a, int[] buf, int lo, int hi) {
        if (hi - lo <= CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >>> 1;
        mergeSort(a, buf, lo, mid);
        mergeSort(a, buf, mid, hi);
        merge(a, buf, lo, mid, hi);
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi) {
        int i = lo, j = mid, k = lo;
        while (i < mid && j < hi)
            buf[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i < mid) buf[k++] = a[i++];
        while (j < hi) buf[k++] = a[j++];
        System.arraycopy(buf, lo, a, lo, hi - lo);
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && a[j] > key) a[j + 1] = a[j--];
            a[j + 1] = key;
        }
    }
}
