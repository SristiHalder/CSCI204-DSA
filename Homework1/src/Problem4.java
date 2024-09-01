public class Problem4 {
    static void bubble_sort(int[] a) {                   // Cost      Time  (Worst Case)
        for (int i = 0; i < a.length - 1; i++) {         //   C1       n-1
            for (int j = a.length - 1; j > i; j--) {     //   C2       (n-1) + (n-2) + (n-3) ... + 1 = n(n - 1) / 2
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                }
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

//T(n)= C1(n - 1) + C2(n(n - 1) / 2)
// Therefore it is of quadratic time complexity (since there is n^2)
