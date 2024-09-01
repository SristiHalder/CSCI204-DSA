public class Problem3 {
    public static <T extends Comparable<T>> int binary_search_rec(T[] a, T x, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) / 2;

        int comparisonResult = a[mid].compareTo(x);
        if (comparisonResult == 0)
            return mid;
        else if (comparisonResult > 0)
            return binary_search_rec(a, x, start, mid - 1);
        else
            return binary_search_rec(a, x, mid + 1, end);
    }

    public static void main(String[] args) {
        // Sample Data
        Integer[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        Integer target = 7;

        int index = binary_search_rec(arr, target, 0, arr.length - 1);

        System.out.println("Index is at: " + index);
    }
}
