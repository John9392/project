import java.util.Arrays;

public class DynamicArray {
    private int[] array;
    private int size;
    private int capacity;

    public DynamicArray() {
        this.capacity = 10;
        this.array = new int[capacity];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            array = Arrays.copyOf(array, capacity);
        }
    }

    public void insert(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int deletedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deletedElement;
    }

    public int find(int element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public void sort() {
        quickSort(array, 0, size - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public void merge(DynamicArray otherArray) {
        for (int i = 0; i < otherArray.size; i++) {
            ensureCapacity();
            array[size++] = otherArray.array[i];
        }
    }

    public void reverse() {
        int start = 0;
        int end = size - 1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.insert(0, 1);
        dynamicArray.insert(1, 3);
        dynamicArray.insert(1, 2);

        System.out.println("After insertion: " + dynamicArray);

        dynamicArray.delete(1);

        System.out.println("After deletion: " + dynamicArray);

        int index = dynamicArray.find(3);
        System.out.println("Found element 3 at index: " + index);

        dynamicArray.sort();
        System.out.println("After sorting: " + dynamicArray);

        DynamicArray anotherArray = new DynamicArray();
        anotherArray.insert(0, 4);
        anotherArray.insert(1, 5);

        dynamicArray.merge(anotherArray);
        System.out.println("After merging: " + dynamicArray);

        dynamicArray.reverse();
        System.out.println("After reversing: " + dynamicArray);
    }
}