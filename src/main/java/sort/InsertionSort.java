package sort;

import java.util.List;

public class InsertionSort {

    public static void insertionSortArray(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            // Перемещаем элементы, которые больше ключа, на одну позицию вперед
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void insertionSortList(List<Integer> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            int key = list.get(i);
            int j = i - 1;

            // Сдвигаем элементы, которые больше ключа, на одну позицию вперед
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }

            // Вставляем ключ в правильное положение
            list.set(j + 1, key);
        }
    }
}