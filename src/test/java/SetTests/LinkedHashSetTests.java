package SetTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LinkedHashSetTests {
    LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
    LinkedHashSet<String> linkedHashSet2 = new LinkedHashSet<>();


    @Order(1)
    @Test
    @Description("Добавление элемента в список")
    void testAdd() {
        linkedHashSet.add("One_Test");
        linkedHashSet.add("Two_Test");
        linkedHashSet.add("Three_Test");
        Assertions.assertEquals(linkedHashSet.toString(), "[One_Test, Two_Test, Three_Test]");
    }

    @Order(2)
    @Test
    @Description("Добавление элементов коллекции в список")
    void testAddAll() {
        linkedHashSet2.add("One_Test");
        linkedHashSet2.add("Two_Test");
        linkedHashSet.addAll(linkedHashSet2);
        Assertions.assertEquals(linkedHashSet.toString(), linkedHashSet2.toString());
    }

    @Order(3)
    @Test
    @Description("Получение размера коллекции")
    void testGetSize() {
        testAdd();
        int a = linkedHashSet.size();
        Assertions.assertEquals(a, 3);
    }

    @Order(4)
    @Test
    @Description("Проверка на наличие элементов в коллекции")
    void testIsEmpty() {
        testAdd();
        if (!linkedHashSet.isEmpty()) {
            Assertions.assertEquals(linkedHashSet.toString(), "[One_Test, Two_Test, Three_Test]");
        }
    }

    @Order(5)
    @Test
    @Description("Проверка, содержит ли список определенный элемент")
    void testContains() {
        testAdd();
        if (linkedHashSet.contains("One_Test")) {
            Assertions.assertEquals(linkedHashSet.toString(), "[One_Test, Two_Test, Three_Test]");
        }
    }

    @Order(6)
    @Test
    @Description("Проверка значения итератора с ожидаемыми элементами и " +
            "проверяем, что размер списка совпадает с количеством ожидаемых элементов")
    void testIterator() {
        testAdd();
        Iterator<String> iterator = linkedHashSet.iterator();
        String[] expected = {"One_Test", "Two_Test", "Three_Test"};
        int index = 0;
        while (iterator.hasNext()) {
            String a = iterator.next();
            Assertions.assertEquals(a, expected[index]);
            index++;
        }
        Assertions.assertEquals(linkedHashSet.size(), index);
    }

    @Order(7)
    @Test
    @Description("Проверка каждого элемента из списка с ожидаемым списком")
    void testForEach() {
        testAdd();
        linkedHashSet2.add("One_Test");
        linkedHashSet2.add("Two_Test");
        linkedHashSet2.add("Three_Test");
        linkedHashSet.forEach(element -> {
            String expectedElement = linkedHashSet2.removeFirst();
            System.out.println(expectedElement);
            Assertions.assertEquals(element, expectedElement);
        });
    }

    @Order(8)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArray() {
        testAdd();
        linkedHashSet2.add("One_Test");
        linkedHashSet2.add("Two_Test");
        linkedHashSet2.add("Three_Test");
        Assertions.assertArrayEquals(linkedHashSet.toArray(), linkedHashSet2.toArray());
    }

    @Order(9)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArrayGenerator() {
        testAdd();
        // Преобразуем в массив с использованием toArray(IntFunction)
        String[] actualArray = linkedHashSet.toArray(String[]::new);
        String[] expectedArray = {"One_Test", "Two_Test", "Three_Test"};
        Assertions.assertArrayEquals(expectedArray, actualArray);
    }

    @Order(10)
    @Test
    @Description("Удаление элемента по значению из коллекции")
    void testRemove() {
        testAdd();
        linkedHashSet.remove("One_Test");
        Assertions.assertEquals(linkedHashSet.toString(), "[Two_Test, Three_Test]");
    }

    @Order(11)
    @Test
    @Description("Удаляет элемент Two_Test, для которого предикат возвращает true")
    void testRemoveIf() {
        testAdd();
        linkedHashSet.removeIf(n -> n.equals("Two_Test"));
        Assertions.assertEquals(linkedHashSet.toString(), "[One_Test, Three_Test]");
    }

    @Order(12)
    @Test
    @Description("После удаления элементов в списке проверяем, что размер списка стал 0")
    void testClear() {
        testAdd();
        linkedHashSet.clear();
        Assertions.assertEquals(linkedHashSet.size(), 0);
    }

    @Order(13)
    @Test
    @Description("Итерируем по элементам с помощью Spliterator и добавляем их в список и" +
            "проверяем, что список, полученный с помощью Spliterator, совпадает с исходным")
    void testSpliterator() {
        testAdd();
        Spliterator<String> spliterator = linkedHashSet.spliterator();
        spliterator.forEachRemaining(linkedHashSet2::add);
        Assertions.assertEquals(linkedHashSet.toString(), linkedHashSet2.toString());
    }

    @Order(14)
    @Test
    @Description("Фильтрация по длине строки, и проверка, что результат содержит элементы, удовлетворяющие условию")
    void testStream() {
        testAdd();
        linkedHashSet.add("Three_Test");
        linkedHashSet.add("Four_Test");
        List<String> expectedList = List.of("Three_Test");
        // stream() для фильтрации элементов и сбора в новый список
        List<String> actualList = linkedHashSet.stream()
                .filter(s -> s.length() > 9)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Order(15)
    @Test
    @Description("Параллельно выполняется фильтрация элементов, и результат сравнивается с ожидаемым значением")
    void testParallelStream() {
        testAdd();
        linkedHashSet.add("Three_Test");
        linkedHashSet.add("Four_Test");
        List<String> expectedList = List.of("Three_Test", "Four_Test");
        List<String> actualList = linkedHashSet.parallelStream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Order(16)
    @Test
    @Description("Удаление элементов из текущей коллекции, которые содержатся в другой указанной коллекции")
    void testRemoveAll() {
        testAddAll();
        linkedHashSet.removeAll(linkedHashSet2);
        Assertions.assertEquals(linkedHashSet.toString(), "[]");
    }

    @Order(17)
    @Test
    @Description("Сравниваем исходный список после удаления с ожидаемым результатом," +
            "удаляет из исходного списка все элементы, кроме тех, которые есть в переданной коллекции")
    void testRetainAll() {
        testAddAll();
        linkedHashSet.retainAll(linkedHashSet2);
        Assertions.assertEquals(linkedHashSet.toString(), linkedHashSet.toString());
    }


    @AfterEach
    void display() {
        System.out.println(linkedHashSet.toString());
    }
}
