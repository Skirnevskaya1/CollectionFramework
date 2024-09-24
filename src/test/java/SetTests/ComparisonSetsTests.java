package SetTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

public class ComparisonSetsTests {

    HashSetTests hashSetTests = new HashSetTests();
    LinkedHashSetTests linkedHashSetTests = new LinkedHashSetTests();

    @BeforeEach
    void add() {
        hashSetTests.testAdd();
        linkedHashSetTests.testAdd();
    }

    @Order(1)
    @Test
    @Description("Проверка двух списков на их одиноковое содержимое")
    void testAdd() {
        Assertions.assertEquals(hashSetTests.hashSet, linkedHashSetTests.linkedHashSet);
    }

    @Order(2)
    @Test
    @Description("Добавление элементов коллекции в список")
    void testAddAll() {
        hashSetTests.hashSet.addAll(linkedHashSetTests.linkedHashSet);
        Assertions.assertEquals(hashSetTests.hashSet, linkedHashSetTests.linkedHashSet);
    }

    @Order(3)
    @Test
    @Description("Получение размера коллекции")
    void testGetSize() {
        int a = hashSetTests.hashSet.size();
        int b = linkedHashSetTests.linkedHashSet.size();
        Assertions.assertEquals(a, 3);
        Assertions.assertEquals(b, 3);

    }

    @Order(4)
    @Test
    @Description("Проверка на наличие элементов в коллекции")
    void testIsEmpty() {
        if (!hashSetTests.hashSet.isEmpty()) {
            String[] actualArray = hashSetTests.hashSet.toArray(new String[0]);
            Arrays.sort(actualArray);
            String[] expectedArray = linkedHashSetTests.linkedHashSet.toArray(new String[0]);
            Arrays.sort(expectedArray);
            Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
        }
    }

    @Order(5)
    @Test
    @Description("Проверка, содержит ли список определенный элемент")
    void testContains() {
        testAdd();
        if (hashSetTests.hashSet.contains("One_Test") && linkedHashSetTests.linkedHashSet.contains("One_Test")) {
            String[] actualArray = hashSetTests.hashSet.toArray(new String[0]);
            String[] expectedArray = linkedHashSetTests.linkedHashSet.toArray(new String[0]);
            Arrays.sort(actualArray);
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
        Iterator<String> iterator = hashSetTests.hashSet.iterator();
        Iterator<String> iterator2 = linkedHashSetTests.linkedHashSet.iterator();
        int index = 0;
        while (iterator.hasNext() && iterator2.hasNext()) {
            String a = iterator.next();
            String b = iterator2.next();
            //Assertions.assertEquals(a, b);
            index++;
        }
        Assertions.assertEquals(hashSetTests.hashSet.size(), index);
        Assertions.assertEquals(linkedHashSetTests.linkedHashSet.size(), index);

    }

    @Order(7)
    @Test
    @Description("Проверка каждого элемента из списка с ожидаемым списком")
    void testForEach() {
        List<String> list = new ArrayList<>();
        hashSetTests.hashSet.forEach(list::add);
        String[] actualArray = list.toArray(new String[0]);
        // Ожидаемый массив (порядок элементов может отличаться, так как HashSet не гарантирует порядок)
        List<String> list2 = new ArrayList<>();
        linkedHashSetTests.linkedHashSet.forEach(list2::add);
        String[] expectedArray = list2.toArray(new String[0]);
        java.util.Arrays.sort(actualArray);
        java.util.Arrays.sort(expectedArray);
        Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
    }


    @Order(8)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArrayGenerator() {
        testAdd();
        // Преобразуем в массив с использованием toArray(IntFunction)
        String[] actualArray = hashSetTests.hashSet.toArray(String[]::new);
        String[] expectedArray = linkedHashSetTests.linkedHashSet.toArray(String[]::new);
        java.util.Arrays.sort(actualArray);
        java.util.Arrays.sort(expectedArray);
        Assertions.assertEquals(Arrays.toString(actualArray), Arrays.toString(expectedArray));
    }

    @Order(9)
    @Test
    @Description("Удаление элемента по значению из коллекции")
    void testRemove() {
        hashSetTests.hashSet.remove("One_Test");
        linkedHashSetTests.linkedHashSet.remove("One_Test");
        Assertions.assertEquals(hashSetTests.hashSet.toString(), linkedHashSetTests.linkedHashSet.toString());
    }

    @Order(10)
    @Test
    @Description("Удаляет элемент Two_Test, для которого предикат возвращает true")
    void testRemoveIf() {
        hashSetTests.hashSet.removeIf(n -> n.equals("Two_Test"));
        linkedHashSetTests.linkedHashSet.removeIf(n -> n.equals("Two_Test"));
        Assertions.assertEquals(hashSetTests.hashSet.toString(), linkedHashSetTests.linkedHashSet.toString());
    }

    @Order(11)
    @Test
    @Description("После удаления элементов в списке проверяем, что размер списка стал 0")
    void testClear() {
        hashSetTests.hashSet.clear();
        linkedHashSetTests.linkedHashSet.clear();
        Assertions.assertEquals(hashSetTests.hashSet.size(), linkedHashSetTests.linkedHashSet.size());
    }

    @Order(12)
    @Test
    @Description("Итерируем по элементам с помощью Spliterator и добавляем их в список и" +
            "проверяем, что список, полученный с помощью Spliterator, совпадает с исходным")
    void testSpliterator() {
        testAdd();
        Spliterator<String> spliterator = hashSetTests.hashSet.spliterator();
        spliterator.forEachRemaining(linkedHashSetTests.linkedHashSet::add);
        Assertions.assertEquals(hashSetTests.hashSet, linkedHashSetTests.linkedHashSet);
    }


    @Order(13)
    @Test
    @Description("Фильтрация по длине строки, и проверка, что результат содержит элементы, удовлетворяющие условию")
    void testStream() {
        hashSetTests.hashSet.add("Three_Test");
        hashSetTests.hashSet.add("Four_Test");
        List<String> expectedList = List.of("Three_Test");
        // stream() для фильтрации элементов и сбора в новый список
        List<String> actualList = hashSetTests.hashSet.stream()
                .filter(s -> s.length() > 9)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Order(14)
    @Test
    @Description("Параллельно выполняется фильтрация элементов, и результат сравнивается с ожидаемым значением")
    void testParallelStream() {
        // Используем parallelStream для сбора элементов в список
        List<String> actualList = hashSetTests.hashSet.parallelStream().toList();
        String[] actualArray = actualList.toArray(new String[0]);
        List<String> actualList1 = linkedHashSetTests.linkedHashSet.parallelStream().toList();
        String[] expectedArray = actualList1.toArray(new String[0]);
        // Сортируем массивы перед сравнением, так как HashSet и parallelStream не гарантируют порядок
        java.util.Arrays.sort(actualArray);
        java.util.Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, actualArray);
    }

    @Order(14)
    @Test
    @Description("Удаление элементов из текущей коллекции, которые содержатся в другой указанной коллекции")
    void testRemoveAll() {
        hashSetTests.hashSet.removeAll(linkedHashSetTests.linkedHashSet);
        Assertions.assertEquals(hashSetTests.hashSet.toString(), "[]");
    }

    @Order(17)
    @Test
    @Description("Сравниваем исходный список после удаления с ожидаемым результатом," +
            "удаляет из исходного списка все элементы, кроме тех, которые есть в переданной коллекции")
    void testRetainAll() {
        testAddAll();
        hashSetTests.hashSet.retainAll(linkedHashSetTests.linkedHashSet);
        Assertions.assertEquals(hashSetTests.hashSet, linkedHashSetTests.linkedHashSet);
    }

    @AfterEach
    void display() {
        System.out.println(hashSetTests.hashSet.toString() + "\n"
                + linkedHashSetTests.linkedHashSet.toString() + "\n");
    }
}
