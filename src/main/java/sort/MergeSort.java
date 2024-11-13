package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort {

    // Массив для слияния, который будет использоваться на протяжении всей сортировки
    private static int[] tempArray;

    // Основной метод сортировки
    public static void mergeSortArray(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        // Инициализируем временный массив для слияния
        tempArray = new int[array.length];

        // Запускаем рекурсивный метод сортировки
        mergeSortArray(array, 0, array.length - 1);
    }

    // Рекурсивный метод сортировки с безопасным вычислением среднего индекса
    private static void mergeSortArray(int[] array, int low, int high) {
        if (low < high) {
            // Безопасное вычисление среднего индекса
            int mid = low + (high - low) / 2;

            // Рекурсивно сортируем две половины
            mergeSortArray(array, low, mid);
            mergeSortArray(array, mid + 1, high);

            // Сливаем отсортированные половины в исходный массив
            mergeArray(array, low, mid, high);
        }
    }

    // Метод слияния двух отсортированных подмассивов
    private static void mergeArray(int[] array, int low, int mid, int high) {
        // Копируем данные из исходного массива в временный массив
        for (int i = low; i <= high; i++) {
            tempArray[i] = array[i];
        }

        int i = low;      // Индекс для левой половины
        int j = mid + 1;  // Индекс для правой половины
        int k = low;      // Индекс для исходного массива

        // Слияние двух отсортированных подмассивов
        while (i <= mid && j <= high) {
            if (tempArray[i] <= tempArray[j]) {
                array[k++] = tempArray[i++];
            } else {
                array[k++] = tempArray[j++];
            }
        }

        // Копируем оставшиеся элементы левой половины
        while (i <= mid) {
            array[k++] = tempArray[i++];
        }

        // Копируем оставшиеся элементы правой половины
        while (j <= high) {
            array[k++] = tempArray[j++];
        }
    }

    // List merge sort

    public static <T extends Comparable<T>> void mergeSortList(List<T> list) {
        List<T> temp = new ArrayList<>(Collections.nCopies(list.size(), null));
        mergeSort(list, temp, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(List<T> list, List<T> temp, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2; // Безопасное вычисление среднего индекса

            mergeSort(list, temp, low, mid);
            mergeSort(list, temp, mid + 1, high);
            merge(list, temp, low, mid, high);
        }
    }

    private static <T extends Comparable<T>> void merge(List<T> list, List<T> temp, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            temp.set(i, list.get(i));
        }

        int left = low;
        int right = mid + 1;
        int current = low;

        while (left <= mid && right <= high) {
            if (temp.get(left).compareTo(temp.get(right)) <= 0) {
                list.set(current, temp.get(left));
                left++;
            } else {
                list.set(current, temp.get(right));
                right++;
            }
            current++;
        }

        while (left <= mid) {
            list.set(current, temp.get(left));
            left++;
            current++;
        }
    }
}
