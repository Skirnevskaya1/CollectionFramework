package ListTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedListTests {
    List<String> linkList = new LinkedList<>();
    List<String> linkList2 = new LinkedList<>();

    /*
    Тесты на добавление add()
     */
    @Order(1)
    @Test
    @Description("Добавление элемента в конец списка")
    void testAdd() {
        long startTimeArrayList = System.nanoTime();
        linkList.add("One_Test");
        linkList.add("Two_Test");
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Добавление элементов " + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test, Two_Test]");
    }

    @Order(2)
    @Test
    @Description("Добавление элемента на указанную позицию")
    void testAddIndex() {
        long startTimeArrayList = System.nanoTime();
        linkList.add(0, "Two_Test");
        linkList.add(1, "Two_Test");
        linkList.add(2, "Two_Test");
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Добавление элементов на указанную позицию " + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[Two_Test, Two_Test, Two_Test]",
                "Коллекция не соответсвует ожидаемому значению!");
    }

    @Order(3)
    @Test
    @Description("Добавление элемента в нулевую позицию списка")
    void testAddFirst() {
        long startTimeArrayList = System.nanoTime();
        linkList.addFirst("3");
        linkList.addFirst("1");
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Добавление элементов в нулевую позицию списка " + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[1, 3]");
    }

    @Order(4)
    @Test
    @Description("Добавление элемента в конец списка")
    void testAddLast() {
        long startTimeArrayList = System.nanoTime();
        linkList.addLast("1");
        linkList.addLast("5");
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Добавление элементов в конец списка " + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[1, 5]");
    }

    @Order(5)
    @Test
    @Description("Добавление всех элементов из переданной коллекции в текущую коллекцию")
    void testAddAll() {
        testAdd();
        linkList2.add("Three_Test");
        linkList2.add("Four_Test");
        long startTimeArrayList = System.nanoTime();
        linkList.addAll(linkList2);
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Добавление всех элементов из переданной коллекции в текущую коллекцию " + linkList.toString() + " LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test, Two_Test, Three_Test, Four_Test]");
    }

    @Order(6)
    @Test
    @Description("Добавление всех элементов из переданной коллекции в текущую коллекцию на указанную позицию")
    void testAddAllIndex() {
        testAdd();
        linkList2.add("Test");
        long startTimeArrayList = System.nanoTime();
        linkList.addAll(1, linkList2);
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Добавление всех элементов из переданной коллекции в текущую коллекцию на указанную позицию "
                + linkList.toString() + " LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test, Test, Two_Test]");
    }

        /*
    Тесты на удаление remove()
     */

    @Order(7)
    @Test
    @Description("Удаление элемента по значению из коллекции")
    void testRemove() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        linkList.remove("One_Test");
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Удаление элемента по значению из коллекции "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[Two_Test]");
    }

    @Order(8)
    @Test
    @Description("Удаление элемента по указанному индексу")
    void testRemoveIndex() throws IndexOutOfBoundsException {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        linkList.remove(1);
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Удаление элемента по указанному индексу "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test]");
    }

    @Order(9)
    @Test
    @Description("Удаление первого элемента в списке")
    void testRemoveFirst() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        linkList.removeFirst();
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Удаление первого элемента в списке "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[Two_Test]");
    }

    @Order(10)
    @Test
    @Description("Удаление последнего элемента в списке")
    void testRemoveLast() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        linkList.removeLast();
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Удаление последнего элемента в списке "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test]");
    }

    @Order(11)
    @Test
    @Description("Удаляет элемент Two_Test, для которого предикат возвращает true")
    void testRemoveIf() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        linkList.removeIf(n -> n.equals("Two_Test"));
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Удаляет элемент, для которого предикат возвращает true "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test]");
    }

    @Order(12)
    @Test
    @Description("Удаление элементов из текущей коллекции, которые содержатся в другой указанной коллекции")
    void testRemoveAll() {
        testAddAll();
        long startTimeArrayList = System.nanoTime();
        linkList.removeAll(linkList2);
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Удаление элементов из текущей коллекции, которые содержатся в другой указанной коллекции "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(linkList.toString(), "[One_Test, Two_Test]");
    }

            /*
    Тесты на получение get()
     */

    @Order(13)
    @Test
    @Description("Получение элемента по индексу")
    void testGet() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        String a = linkList.get(1);
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Получение элемента по индексу "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(a, "Two_Test");
    }

    @Order(14)
    @Test
    @Description("Получение первого элемента")
    void testGetFirst() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        String a = linkList.getFirst();
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Получение первого элемента "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(a, "One_Test");
    }

    @Order(15)
    @Test
    @Description("Получение последнего элемента")
    void testGetLast() {
        testAdd();
        long startTimeArrayList = System.nanoTime();
        String a = linkList.getLast();
        long endTimeLinkedList = System.nanoTime();
        long durationLinkedList = endTimeLinkedList - startTimeArrayList;
        System.out.println("Получение последнего элемента "
                + linkList.toString() + " в LinkedList заняло " + durationLinkedList + " наносекунд.");
        Assertions.assertEquals(a, "Two_Test");
    }

                /*
    Тесты на наличие элементов, итерации,     в коллекции
     */

    @Order(16)
    @Test
    @Description("Получение размера коллекции")
    void testGetSize() {
        testAdd();
        int a = linkList.size();
        Assertions.assertEquals(a, 2);
    }

    @Order(17)
    @Test
    @Description("Проверка на наличие элементов в коллекции")
    void testIsEmpty() {
        testAdd();
        if (!linkList.isEmpty()) {
            Assertions.assertEquals(linkList.toString(), "[One_Test, Two_Test]");
        }
    }

    @Order(18)
    @Test
    @Description("Проверка, содержит ли список определенный элемент")
    void testContains() {
        testAdd();
        if (linkList.contains("Two_Test")) {
            Assertions.assertEquals(linkList.toString(), "[One_Test, Two_Test]");
        }
    }

    @Order(19)
    @Test
    @Description("Проверка значения итератора с ожидаемыми элементами и " +
            "проверяем, что размер списка совпадает с количеством ожидаемых элементов")
    void testIterator() {
        testAdd();
        Iterator<String> iterator = linkList.iterator();
        String[] expected = {"One_Test", "Two_Test"};
        int index = 0;
        while (iterator.hasNext()) {
            String a = iterator.next();
            Assertions.assertEquals(a, expected[index]);
            index++;
        }
        Assertions.assertEquals(linkList.size(), index);
    }

    @Order(20)
    @Test
    @Description("Проверка каждого элемента из списка с ожидаемым списком")
    void testForEach() {
        testAdd();
        linkList2.add("One_Test");
        linkList2.add("Two_Test");

        linkList.forEach(element -> {
            String expectedElement = linkList2.removeFirst();
            System.out.println(expectedElement);
            Assertions.assertEquals(element, expectedElement);
        });

    }

    @Order(21)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArray() {
        testAdd();
        linkList2.add("One_Test");
        linkList2.add("Two_Test");
        Assertions.assertArrayEquals(linkList.toArray(), linkList2.toArray());
    }

    @Order(22)
    @Test
    @Description("Проверка равенств двух массивов, где произведено преобразования Stream в массив определенного типа")
    void testToArrayStream() {
        Stream<String> stream = Stream.of("One_Test", "Two_Test");
        String[] expected = {"One_Test", "Two_Test"};
        String[] actual = stream.toArray(String[]::new);
        Assertions.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Order(23)
    @Test
    @Description("Преобразовали все элементы в верхний регистр и проверили со списком," +
            " где содержатся элементы в верхнем регистре")
    void testReplaceAll() {
        testAdd();
        UnaryOperator<String> toUpper = String::toUpperCase;
        linkList.replaceAll(toUpper);
        linkList2.add("ONE_TEST");
        linkList2.add("TWO_TEST");
        Assertions.assertEquals(linkList2, linkList);
    }

    @Order(24)
    @Test
    @Description("Сортируем список в порядке возрастания и проверяем с ожидаемым списком")
    void testSort() {
        testAdd();
        linkList.sort(Comparator.naturalOrder());
        linkList2.add("One_Test");
        linkList2.add("Two_Test");
        Assertions.assertEquals(linkList2, linkList);
    }

    @Order(25)
    @Test
    @Description("После удаления элементов в списке проверяем, что размер списка стал 0")
    void testClear() {
        testAdd();
        linkList.clear();
        Assertions.assertEquals(linkList.size(), 0);
    }

    @Order(26)
    @Test
    @Description("Заменяем элемент по индексу и проверяем список с ожидаемым результатом, произошла ли замена успешно")
    void testSet() {
        testAdd();
        linkList.set(1, "Three_Test");
        Assertions.assertEquals(linkList.toString(), "[One_Test, Three_Test]");
    }

    @Order(27)
    @Test
    @Description("Проверяем индекс первого вхождения элемента, возвращает индекс")
    void testIndexOf() {
        testAdd();
        Assertions.assertEquals(linkList.indexOf("One_Test"), 0);
    }

    @Order(28)
    @Test
    @Description("Проверяем индекс последнего вхождения элемента, возвращает индекс")
    void testLastIndexOf() {
        testAdd();
        linkList.add("One_Test");
        Assertions.assertEquals(linkList.lastIndexOf("One_Test"), 2);
    }

    @Order(30)
    @Test
    @Description("Проверка на корректность работы итерированности по элементам списка по индексу в обоих направлениях (вперёд и назад)," +
            "а также модифицировать элементы в процессе итерации")
    void testListIteratorIndex() {
        testAdd();
        ListIterator<String> iterator = linkList.listIterator(0);

        // Итерируемся по элементам списка
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("One_Test", iterator.next());

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("Two_Test", iterator.next());

        // Проверяем обратное направление итерации
        Assertions.assertTrue(iterator.hasPrevious());
        Assertions.assertEquals("Two_Test", iterator.previous());

        Assertions.assertTrue(iterator.hasPrevious());
        Assertions.assertEquals("One_Test", iterator.previous());
    }

    @Order(31)
    @Test
    @Description("Проверка на корректность работы итерированности по элементам списка в обоих направлениях (вперёд и назад)," +
            "а также модифицировать элементы в процессе итерации")
    void testListIterator() {
        testAdd();
        ListIterator<String> iterator = linkList.listIterator(0);

        // Итерируемся по элементам списка
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("One_Test", iterator.next());

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("Two_Test", iterator.next());

        // Проверяем обратное направление итерации
        Assertions.assertTrue(iterator.hasPrevious());
        Assertions.assertEquals("Two_Test", iterator.previous());

        Assertions.assertTrue(iterator.hasPrevious());
        Assertions.assertEquals("One_Test", iterator.previous());
    }

    @Order(32)
    @Test
    @Description("Получаем подсписок от индекса 1 до 3 и" +
            "проверяем, что подсписок соответствует ожидаемым значениям")
    void testSubList() {
        testAdd();
        linkList.add("Three_Test");
        linkList.add("Four_Test");
        List<String> expectedSubList = List.of("Two_Test", "Three_Test");
        List<String> actualSubList = linkList.subList(1, 3);
        Assertions.assertEquals(expectedSubList, actualSubList);
    }

    /*
     метод используется для параллельной обработки данных в Stream API,
     а также для итерации с дополнительными характеристиками, такими как возможность разделения работы
     */
    @Order(33)
    @Test
    @Description("Итерируем по элементам с помощью Spliterator и добавляем их в список и" +
            "проверяем, что список, полученный с помощью Spliterator, совпадает с исходным")
    void testSpliterator() {
        testAdd();
        Spliterator<String> spliterator = linkList.spliterator();
        List<String> actualList = new ArrayList<>();
        spliterator.forEachRemaining(actualList::add);
        Assertions.assertEquals(linkList, actualList);
    }

    @Order(34)
    @Test
    @Description("Фильтрация по длине строки, и проверка, что результат содержит элементы, удовлетворяющие условию")
    void testStream() {
        testAdd();
        linkList.add("Three_Test");
        linkList.add("Four_Test");
        List<String> expectedList = List.of("Three_Test");
        // stream() для фильтрации элементов и сбора в новый список
        List<String> actualList = linkList.stream()
                .filter(s -> s.length() > 9)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Order(35)
    @Test
    @Description("Параллельно выполняется фильтрация элементов, и результат сравнивается с ожидаемым значением")
    void testParallelStream() {
        testAdd();
        linkList.add("Three_Test");
        linkList.add("Four_Test");
        List<String> expectedList = List.of("Three_Test", "Four_Test");
        List<String> actualList = linkList.parallelStream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toList());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Order(36)
    @Test
    @Description("Проверяем, что список в обратном порядке")
    void testRevers() {
        testAdd();
        LinkedList<String> expectedReversedList = new LinkedList<>();
        expectedReversedList.add("Two_Test");
        expectedReversedList.add("One_Test");
        // Инвертируем порядок элементов в исходном списке
        Collections.reverse(linkList);
        Assertions.assertEquals(expectedReversedList, linkList);
    }

    @Order(37)
    @Test
    @Description("Сравниваем исходный список после удаления с ожидаемым результатом," +
            "удаляет из исходного списка все элементы, кроме тех, которые есть в переданной коллекции")
    void testRetainAll() {
        testAdd();
        linkList.add("Three_Test");
        linkList.add("Four_Test");
        // Создаем коллекцию, элементы которой должны остаться в списке
        LinkedList<String> retainList = new LinkedList<>();
        retainList.add("One_Test");
        retainList.add("Two_Test");
        // Ожидаемый результат после применения retainAll()
        LinkedList<String> expectedList = new LinkedList<>();
        expectedList.add("One_Test");
        expectedList.add("Two_Test");
        linkList.retainAll(retainList);
        Assertions.assertEquals(expectedList, linkList);
    }

    @AfterEach
    void display() {
        System.out.println(linkList.toString());
    }

    @Override
    public String toString() {
        return "ListTests.LinkedListTests{" +
                "linkList=" + linkList +
                '}';
    }
}
