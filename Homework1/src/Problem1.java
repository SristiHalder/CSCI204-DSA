
public class Problem1 {
    public static int search(int[] arr, int i, int x) {    // Cost        Times
        if (i >= arr.length)                               //  C1           1
            return -1;

        if (arr[i] == x)                                  //   C2           n
            return i;

        return search(arr, i + 1, x);                  //    C3          n-1
    }



    public static void main (String[] args) {
        //Sample Data (Worst Case)
        int[] array = {3, 5, 2, 4, 10};
        int find = 9;
        int result = search(array, 0, find);

        System.out.println("Element found at index: " + result);
    }
}

//Time-Complexity for Worst Case:
//T(n) = C1(1) + C2(n) + C3(n-1)
//T(n) = C1 + nC2 + nC3 - C3
//T(n) = C1 - C3 + n(C2+C3)
//T(n) = a --> C1 - C3 b-->(C2+C3)
//T(n) = a + bn
// Therefore, it is of linear complexity

