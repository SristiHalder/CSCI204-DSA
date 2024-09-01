import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class ArrayDescending {

    public static void main(String[] args) throws IOException {
        String fileName = "Random_Array.txt";
        generateRandomArrayToFile(fileName, 1000000);

        long[] array = readArrayFromFile(fileName, 1000000);

        // Sort the array in ascending order first using Java's built-in sort
        Arrays.sort(array);
        // Reverse the array to get it in descending order
        reverseArray(array);

        // Proceed to measure sort times on the sorted array
        System.out.println("Performance on sorted array in descending order:");

        System.out.println("\nQuicksort:");
        measureSortTime(array, ArrayDescending::quicksort);

        System.out.println("\nMerge Sort:");
        measureSortTime(array, ArrayDescending::mergeSort);

        System.out.println("\nInsertion Sort:");
        measureSortTime(array, ArrayDescending::insertionSort);

        System.out.println("\nSelection Sort:");
        measureSortTime(array, ArrayDescending::selectionSort);

    }

    // Method to reverse the array
    private static void reverseArray(long[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            long temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }


    private static void generateRandomArrayToFile(String fileName, int size) throws FileNotFoundException {
        Random rand = new Random();
        try (PrintWriter outFS = new PrintWriter(new FileOutputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                outFS.println(rand.nextLong());
            }
        }
    }
    private static long[] readArrayFromFile(String fileName, int size) throws FileNotFoundException {
        long[] array = new long[size];
        try (Scanner inFS = new Scanner(new FileInputStream(fileName))) {
            for (int i = 0; i < size; i++) {
                if (inFS.hasNextLong()) {
                    array[i] = inFS.nextLong();
                }
            }
        }
        return array;
    }

    private static void measureSortTime(long[] array, SortFunction function) {
        long[] arrayCopy = array.clone();
        double startTime = System.currentTimeMillis();
        function.sort(arrayCopy);
        double estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated time (ms): " + estimatedTime);
    }

    public static void insertionSort(long[] array) {
        for (int j = 1; j < array.length; j++) {
            long temp = array[j];
            int i = j - 1;
            while ((i > -1) && (array[i] > temp)) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = temp;
        }
    }

    public static void selectionSort(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            long temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void mergeSort(long[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static void mergeSort(long[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid + 1, end);
            merge(a, start, mid, end);
        }
    }

    public static void merge(long[] a, int start, int mid, int end) {
        long[] temp = new long[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= end) {
            temp[k++] = a[j++];
        }

        System.arraycopy(temp, 0, a, start, temp.length);
    }


    public static void quicksort(long[] array) {
        if (array.length > 1) {
            quicksort(array, 0, array.length - 1);
        }
    }

    private static void quicksort(long[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1); // Sort the left part
            quicksort(array, pivotIndex + 1, high); // Sort the right part
        }
    }

    private static int partition(long[] array, int low, int high) {
        // Median-of-three pivot selection
        //Using this approach to avoid stack overflow
        int middle = low + (high - low) / 2;
        long pivot = medianOfThree(array[low], array[middle], array[high]);

        int i = low - 1;
        for (int j = low; j <= high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static long medianOfThree(long a, long b, long c) {
        if ((a > b) ^ (a > c)) return a;
        else if ((b < a) ^ (b < c)) return b;
        else return c;
    }

    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}