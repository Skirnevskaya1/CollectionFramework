package sort;

import java.util.Collections;
import java.util.List;

public class QuickSort {

    public static void quickSortArray(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partitionArray(array, low, high);

            quickSortArray(array, low, partitionIndex - 1);
            quickSortArray(array, partitionIndex + 1, high);
        }
    }

    // Метод для разделения массива и возврата индекса опорного элемента
    private static int partitionArray(int[] array, int low, int high) {
        // Используем безопасное вычисление среднего значения
        int mid = low + (high - low) / 2;
        int pivot = array[mid];
        // Перемещаем опорный элемент в конец для удобства
        swapArray(array, mid, high);

        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                swapArray(array, i, j);
                i++;
            }
        }

        // Возвращаем опорный элемент на его окончательную позицию
        swapArray(array, i, high);
        return i;
    }

    private static void swapArray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // List Quick sort

    public static <T extends Comparable<T>> void quickSortList(List<T> list) {
        quickSortList(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void quickSortList(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionList(list, low, high);
            quickSortList(list, low, pivotIndex - 1);
            quickSortList(list, pivotIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partitionList(List<T> list, int low, int high) {
        T pivot = list.get(low + (high - low) / 2);
        int i = low;
        int j = high;

        while (i <= j) {
            while (list.get(i).compareTo(pivot) < 0) {
                i++;
            }
            while (list.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                Collections.swap(list, i, j);
                i++;
                j--;
            }
        }
        return i;
    }
}
