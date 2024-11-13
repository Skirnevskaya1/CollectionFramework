package sort;

import java.util.Collections;
import java.util.List;

public class BubbleSort {

    public static void bubbleSortArray(int[] array) {
        int n = array.length;
        boolean swapped;

        // Внешний цикл для каждого прохода
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Внутренний цикл для сравнения и обмена соседних элементов
            for (int j = 0; j < n - 1 - i; j++) {
                // Сравниваем элементы и меняем их, если они расположены в неправильном порядке
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // Если не было обменов, массив отсортирован
            if (!swapped) {
                break;
            }
        }
    }


    public static void bubbleSortList(List<Integer> list) {
        int n = list.size();
        boolean swapped;

        // Проходим по списку несколько раз
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Сравниваем соседние элементы и меняем их местами, если нужно
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Меняем местами
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }

            // Если на этом шаге не было обменов, список уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }
}
