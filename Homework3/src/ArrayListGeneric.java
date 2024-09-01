public class ArrayListGeneric<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int size;

    // Constructors
    public ArrayListGeneric() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayListGeneric(int capacity) {
        array = (E[]) new Object[capacity];
        size = 0;
    }

    // Add element to the end of the list
    public void add(E element) {
        if (size == array.length) {
            resize();
        }
        array[size++] = element;
    }

    // Get element at index
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return array[index];
    }

    // Print all elements in the list
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Resize the underlying array when it's full
    private void resize() {
        int newSize = array.length * 2;
        E[] newArray = (E[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    // Main method for testing
    public static void main(String[] args) {
        ArrayListGeneric<Integer> list = new ArrayListGeneric<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.print();
    }
}
