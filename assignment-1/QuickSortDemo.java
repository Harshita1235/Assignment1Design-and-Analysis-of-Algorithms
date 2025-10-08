import java.util.*;

public class QuickSortDemo {

    private static final Random rand = new Random();

    public static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            // Randomized pivot selection
            int pivotIndex = low + rand.nextInt(high - low + 1);
            swap(arr, pivotIndex, high);
            int p = partition(arr, low, high);

            // Recurse on smaller partition first
            if (p - low < high - p) {
                quickSort(arr, low, p - 1);
                low = p + 1;  // Tail recursion on the larger side
            } else {
                quickSort(arr, p + 1, high);
                high = p - 1;  // Tail recursion on the larger side
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 8, 10, 1, 2, 1};
        System.out.println("Before: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("After:  " + Arrays.toString(arr));
    }
}
