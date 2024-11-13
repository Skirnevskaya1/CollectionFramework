import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static sort.BubbleSort.bubbleSortArray;
import static sort.BubbleSort.bubbleSortList;
import static sort.InsertionSort.*;
import static sort.MergeSort.mergeSortArray;
import static sort.MergeSort.mergeSortList;
import static sort.QuickSort.quickSortArray;
import static sort.QuickSort.quickSortList;
import static sort.TimSort.timSort;

public class SortsTests {
    int[] array = {64, 34, 25, 12, 22, 11, 90};
    int[] expectedArray = {11, 12, 22, 25, 34, 64, 90};

    List<Integer> list = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
    List<Integer> expected = Arrays.asList(11, 12, 22, 25, 34, 64, 90);


    @Test
    void testBubbleSortArray() {
        bubbleSortArray(array);
        Assertions.assertArrayEquals(expectedArray, array,
                "Массив не отсортирован корректно");
    }

    @Test
    void testBubbleSortList() {
        bubbleSortList(list);
        Assertions.assertEquals(expected, list,
                "Список не отсортирован корректно!");
    }


    @Test
    void testInsertionSortArray() {
        insertionSortArray(array);
        Assertions.assertArrayEquals(expectedArray, array,
                "Массив не отсортирован корректно");
    }

    @Test
    void testInsertionSortList() {
        insertionSortList(list);
        Assertions.assertEquals(expected, list,
                "Список не отсортирован корректно");
    }

    @Test
    void testQuickSortArray() {
        quickSortArray(array, 0, array.length - 1);
        Assertions.assertArrayEquals(expectedArray, array,
                "Массив не отсортирован корректно!");
    }

    @Test
    void testQuickSortList() {
        Collections.sort(list);
        quickSortList(list);
        Assertions.assertEquals(expected, list,
                "Список не отсортирован корректно!");
    }

    @Test
    void testMergeSortArray() {
        mergeSortArray(array);
        Assertions.assertArrayEquals(expectedArray, array,
                "Массив отсортирован неверно");
    }

    @Test
    void testMergeSortList() {
        Collections.sort(list);
        mergeSortList(list);
        Assertions.assertEquals(expected, list,
                "Список не отсортирован корректно!");
    }

    @Test
    public void testSafeMergeSortLargeNumbers() {
        List<Integer> list = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 100000, -100000));
        List<Integer> expected = new ArrayList<>(list);
        Collections.sort(expected);
        mergeSortList(list);
        Assertions.assertEquals(expected, list,
                "Список с большими значениями не отсортирован корректно!");
    }

    @Test
    void testTimSortArray() {
        List<Integer> list = new ArrayList<>(Arrays.asList(64, 25, 12, 22, 11));
        List<Integer> expected = new ArrayList<>(list);
        Collections.sort(expected);
        timSort(list);
        Assertions.assertEquals(expected, list,
                "Список не отсортирован корректно!");
    }
}
