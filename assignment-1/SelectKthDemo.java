import java.util.*;

public class SelectKthDemo {


    public static int selectKth(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k];
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 2; // 0-based index → 3rd smallest element
        System.out.println("Array: " + Arrays.toString(arr));
        int result = selectKth(arr.clone(), k);
        System.out.println((k+1) + "-th smallest = " + result);
    }
}

