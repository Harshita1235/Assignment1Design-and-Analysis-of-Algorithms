import java.util.*;

public class SelectKthDemo {

    public static int selectKth(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] a, int left, int right, int k) {
        while (true) {
            if (left == right) return a[left];

            int pivotIndex = medianOfMedians(a, left, right);
            pivotIndex = partition(a, left, right, pivotIndex);
            int len = pivotIndex - left + 1;

            if (k == pivotIndex) return a[k];
            else if (k < pivotIndex) right = pivotIndex - 1;
            else left = pivotIndex + 1;
        }
    }

    private static int medianOfMedians(int[] a, int left, int right) {
        if (right - left < 5)
            return partition5(a, left, right);

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            int median5 = partition5(a, i, subRight);
            swap(a, median5, left + numMedians++);
        }
        return medianOfMedians(a, left, left + numMedians - 1);
    }

    private static int partition5(int[] a, int left, int right) {
        Arrays.sort(a, left, right + 1);
        return (left + right) >>> 1;
    }

    private static int partition(int[] a, int left, int right, int pivotIndex) {
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (a[i] < pivot) swap(a, store++, i);
        }
        swap(a, store, right);
        return store;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 2; // 0-based index for 3rd smallest
        System.out.println("Array: " + Arrays.toString(arr));
        int result = selectKth(arr.clone(), k);
        System.out.println((k+1) + "-th smallest = " + result);
    }
}
