package sort;

import java.util.Arrays;
import java.util.List;

public class TimSort {

    // Размер подмассива для сортировки вставками
    private static final int RUN = 32;

    public static <T extends Comparable<T>> void timSort(List<T> list) {
        int n = list.size();
        T[] array = (T[]) new Comparable[n];
        list.toArray(array);

        // Сортируем каждый "пакет" с помощью сортировки вставками
        for (int i = 0; i < n; i += RUN) {
            insertionSort(array, i, Math.min((i + RUN - 1), (n - 1)));
        }

        // Слияние подмассивов
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(array, left, mid, right);
                }
            }
        }

        // Переводим массив обратно в список
        list.clear();
        list.addAll(Arrays.asList(array));
    }

    // Сортировка вставками для маленьких подмассивов
    private static <T extends Comparable<T>> void insertionSort(T[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = array[i];
            int j = i - 1;

            // Сдвигаем элементы, которые больше ключа, на одну позицию вправо
            while (j >= left && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Слияние двух отсортированных подмассивов
    private static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        T[] leftArray = (T[]) new Comparable[len1];
        T[] rightArray = (T[]) new Comparable[len2];

        System.arraycopy(array, left, leftArray, 0, len1);
        System.arraycopy(array, mid + 1, rightArray, 0, len2);

        int i = 0, j = 0, k = left;

        // Слияние подмассивов
        while (i < len1 && j < len2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копирование оставшихся элементов
        while (i < len1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < len2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}