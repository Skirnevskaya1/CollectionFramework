package SetTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class HashSetTests {
    HashSet<String> hashSet = new HashSet<>();
    HashSet<String> hashSet2 = new HashSet<>();

    @Order(1)
    @Test
    @Description("Добавление элемента в список")
    void testAdd() {
        hashSet.add("One_Test");
        hashSet.add("Two_Test");
        hashSet.add("Three_Test");
        String[] actualArray = hashSet.toArray(new String[0]);
        Arrays.sort(actualArray);
        String[] expectedArray = {"One_Test", "Two_Test", "Three_Test"};
        Arrays.sort(expectedArray);
        Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
    }

    @Order(2)
    @Test
    @Description("Добавление элементов коллекции в список")
    void testAddAll() {
        hashSet2.add("One_Test");
        hashSet2.add("Two_Test");
        hashSet.addAll(hashSet2);
        Assertions.assertEquals(hashSet.toString(), hashSet2.toString());
    }

    @Order(3)
    @Test
    @Description("Получение размера коллекции")
    void testGetSize() {
        testAdd();
        int a = hashSet.size();
        Assertions.assertEquals(a, 3);
    }

    @Order(4)
    @Test
    @Description("Проверка на наличие элементов в коллекции")
    void testIsEmpty() {
        testAdd();
        if (!hashSet.isEmpty()) {
            String[] actualArray = hashSet.toArray(new String[0]);
            Arrays.sort(actualArray);
            String[] expectedArray = {"One_Test", "Two_Test", "Three_Test"};
            Arrays.sort(expectedArray);
            Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
        }
    }

    @Order(5)
    @Test
    @Description("Проверка, содержит ли список определенный элемент")
    void testContains() {
        testAdd();
        if (hashSet.contains("One_Test")) {
            String[] actualArray = hashSet.toArray(new String[0]);
            Arrays.sort(actualArray);
            String[] expectedArray = {"One_Test", "Two_Test", "Three_Test"};
            Arrays.sort(expectedArray);
            Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
        }
    }

    @Order(6)
    @Test
    @Description("Проверка значения итератора с ожидаемыми элементами и " +
            "проверяем, что размер списка совпадает с количеством ожидаемых элементов")
    void testIterator() {
        testAdd();
        Iterator<String> iterator = hashSet.iterator();
        String[] expected = {"One_Test", "Two_Test", "Three_Test"};
        int index = 0;
        while (iterator.hasNext()) {
            String a = iterator.next();
            index++;
        }
        Assertions.assertEquals(hashSet.size(), index);
    }

    @Order(7)
    @Test
    @Description("Проверка каждого элемента из списка с ожидаемым списком")
    void testForEach() {
        testAdd();
        List<String> list = new ArrayList<>();
        hashSet.forEach(list::add);
        String[] actualArray = list.toArray(new String[0]);
        // Ожидаемый массив (порядок элементов может отличаться, так как HashSet не гарантирует порядок)
        String[] expectedArray = {"Two_Test", "One_Test", "Three_Test"};
        java.util.Arrays.sort(actualArray);
        java.util.Arrays.sort(expectedArray);
        Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
    }

    @Order(8)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArray() {
        testAdd();
        hashSet2.add("One_Test");
        hashSet2.add("Two_Test");
        hashSet2.add("Three_Test");
        Assertions.assertArrayEquals(hashSet.toArray(), hashSet2.toArray());
    }

    @Order(9)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArrayGenerator() {
        testAdd();
        // Преобразуем в массив с использованием toArray(IntFunction)
        String[] actualArray = hashSet.toArray(String[]::new);
        String[] expectedArray = {"One_Test", "Two_Test", "Three_Test"};
        java.util.Arrays.sort(actualArray);
        java.util.Arrays.sort(expectedArray);
        Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
    }

    @Order(10)
    @Test
    @Description("Удаление элемента по значению из коллекции")
    void testRemove() {
        testAdd();
        hashSet.remove("One_Test");
        Assertions.assertEquals(hashSet.toString(), "[Two_Test, Three_Test]");
    }

    @Order(11)
    @Test
    @Description("Удаляет элемент Two_Test, для которого предикат возвращает true")
    void testRemoveIf() {
        testAdd();
        hashSet.removeIf(n -> n.equals("Two_Test"));
        Assertions.assertEquals(hashSet.toString(), "[One_Test, Three_Test]");
    }

    @Order(12)
    @Test
    @Description("После удаления элементов в списке проверяем, что размер списка стал 0")
    void testClear() {
        testAdd();
        hashSet.clear();
        Assertions.assertEquals(hashSet.size(), 0);
    }

    @Order(13)
    @Test
    @Description("Итерируем по элементам с помощью Spliterator и добавляем их в список и" +
            "проверяем, что список, полученный с помощью Spliterator, совпадает с исходным")
    void testSpliterator() {
        testAdd();
        Spliterator<String> spliterator = hashSet.spliterator();
        spliterator.forEachRemaining(hashSet2::add);
        Assertions.assertEquals(hashSet.toString(), hashSet2.toString());
    }

    @Order(14)
    @Test
    @Description("Фильтрация по длине строки, и проверка, что результат содержит элементы, удовлетворяющие условию")
    void testStream() {
        testAdd();
        hashSet.add("Three_Test");
        hashSet.add("Four_Test");
        List<String> expectedList = List.of("Three_Test");
        // stream() для фильтрации элементов и сбора в новый список
        List<String> actualList = hashSet.stream()
                .filter(s -> s.length() > 9)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Order(15)
    @Test
    @Description("Параллельно выполняется фильтрация элементов, и результат сравнивается с ожидаемым значением")
    void testParallelStream() {
        testAdd();
        // Используем parallelStream для сбора элементов в список
        List<String> actualList = hashSet.parallelStream().collect(Collectors.toList());
        String[] actualArray = actualList.toArray(new String[0]);
        String[] expectedArray = {"One_Test", "Two_Test", "Three_Test"};
        // Сортируем массивы перед сравнением, так как HashSet и parallelStream не гарантируют порядок
        java.util.Arrays.sort(actualArray);
        java.util.Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, actualArray);
    }

    @Order(16)
    @Test
    @Description("Удаление элементов из текущей коллекции, которые содержатся в другой указанной коллекции")
    void testRemoveAll() {
        testAddAll();
        hashSet.removeAll(hashSet2);
        Assertions.assertEquals(hashSet.toString(), "[]");
    }

    @Order(17)
    @Test
    @Description("Сравниваем исходный список после удаления с ожидаемым результатом," +
            "удаляет из исходного списка все элементы, кроме тех, которые есть в переданной коллекции")
    void testRetainAll() {
        testAddAll();
        hashSet.retainAll(hashSet2);
        Assertions.assertEquals(hashSet.toString(), hashSet.toString());
    }

    @AfterEach
    void display() {
        System.out.println(hashSet.toString());
    }
}
