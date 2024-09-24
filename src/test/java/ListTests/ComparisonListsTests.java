package ListTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ComparisonListsTests {
    ArrayListTests arrayListTests = new ArrayListTests();
    LinkedListTests linkedListTests = new LinkedListTests();

    @BeforeEach
    void add() {
        arrayListTests.testAdd();
        linkedListTests.testAdd();
    }

    @Order(1)
    @Test
    @Description("Проверка двух списков на их одиноковое содержимое")
    void testAdd() {
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }


    @Order(2)
    @Test
    @Description("Добавление элемента на указанную позицию")
    void testAddIndex() {
        arrayListTests.arrayList.add(0, "Two_Test");
        linkedListTests.linkList.add(0, "Two_Test");
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString(),
                "Коллекции не соответсвует друг другу");
    }

    @Order(3)
    @Test
    @Description("Добавление элемента в нулевую позицию списка")
    void testAddFirst() {
        arrayListTests.arrayList.addFirst("Wow");
        linkedListTests.linkList.addFirst("Wow");
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(4)
    @Test
    @Description("Добавление элемента в конец списка")
    void testAddLast() {
        arrayListTests.arrayList.addLast("Test_5");
        linkedListTests.linkList.addLast("Test_5");
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(5)
    @Test
    @Description("Добавление всех элементов из переданной коллекции в текущую коллекцию")
    void testAddAll() {
        arrayListTests.arrayList.addAll(linkedListTests.linkList);
        Assertions.assertEquals(arrayListTests.arrayList.toString(), "[One_Test, Two_Test, One_Test, Two_Test]");
    }

    @Order(6)
    @Test
    @Description("Добавление всех элементов из переданной коллекции в текущую коллекцию на указанную позицию")
    void testAddAllIndex() {
        arrayListTests.arrayList.addAll(1, linkedListTests.linkList);
        Assertions.assertEquals(arrayListTests.arrayList.toString(), "[One_Test, One_Test, Two_Test, Two_Test]");
    }

        /*
    Тесты на удаление remove()
     */

    @Order(7)
    @Test
    @Description("Удаление элемента по значению из коллекции")
    void testRemove() {
        linkedListTests.linkList.remove("One_Test");
        arrayListTests.arrayList.remove("One_Test");
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(8)
    @Test
    @Description("Удаление элемента по указанному индексу")
    void testRemoveIndex() throws IndexOutOfBoundsException {
        linkedListTests.linkList.remove(1);
        arrayListTests.arrayList.remove(1);
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(9)
    @Test
    @Description("Удаление первого элемента в списке")
    void testRemoveFirst() {
        linkedListTests.linkList.removeFirst();
        arrayListTests.arrayList.removeFirst();
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(10)
    @Test
    @Description("Удаление последнего элемента в списке")
    void testRemoveLast() {
        linkedListTests.linkList.removeLast();
        arrayListTests.arrayList.removeLast();
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(11)
    @Test
    @Description("Удаляет элемент Two_Test, для которого предикат возвращает true")
    void testRemoveIf() {
        linkedListTests.linkList.removeIf(n -> n.equals("Two_Test"));
        arrayListTests.arrayList.removeIf(n -> n.equals("Two_Test"));
        Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
    }

    @Order(12)
    @Test
    @Description("Удаление элементов из текущей коллекции, которые содержатся в другой указанной коллекции")
    void testRemoveAll() {
        linkedListTests.linkList.removeAll(arrayListTests.arrayList);
        Assertions.assertEquals(linkedListTests.linkList.toString(), "[]");
    }

            /*
    Тесты на получение get()
     */

    @Order(13)
    @Test
    @Description("Получение элемента по индексу")
    void testGet() {
        String a = linkedListTests.linkList.get(1);
        String b = arrayListTests.arrayList.get(1);
        Assertions.assertEquals(a, b);
    }

    @Order(14)
    @Test
    @Description("Получение первого элемента")
    void testGetFirst() {
        String a = linkedListTests.linkList.getFirst();
        String b = arrayListTests.arrayList.getFirst();
        Assertions.assertEquals(a, b);
    }

    @Order(15)
    @Test
    @Description("Получение последнего элемента")
    void testGetLast() {
        String a = linkedListTests.linkList.getLast();
        String b = arrayListTests.arrayList.getLast();
        Assertions.assertEquals(a, b);
    }

    /*
    Тесты на наличие элементов, итерации, в коллекции
     */

    @Order(16)
    @Test
    @Description("Получение размера коллекции")
    void testGetSize() {
        int a = linkedListTests.linkList.size();
        int b = arrayListTests.arrayList.size();
        Assertions.assertEquals(a, b);
    }

    @Order(17)
    @Test
    @Description("Проверка на наличие элементов в коллекции")
    void testIsEmpty() {
        testAdd();
        if (!(linkedListTests.linkList.isEmpty() && arrayListTests.arrayList.isEmpty())) {
            Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
        }
    }

    @Order(18)
    @Test
    @Description("Проверка, содержит ли список определенный элемент")
    void testContains() {
        testAdd();
        if (linkedListTests.linkList.contains("Two_Test") && arrayListTests.arrayList.contains("Two_Test")) {
            Assertions.assertEquals(arrayListTests.arrayList.toString(), linkedListTests.linkList.toString());
        }
    }

    @Order(19)
    @Test
    @Description("Проверка значения итератора с ожидаемыми элементами и " +
            "проверяем, что размер списка совпадает с количеством ожидаемых элементов")
    void testIterator() {
        Iterator<String> iterator = linkedListTests.linkList.iterator();
        Iterator<String> iterator2 = arrayListTests.arrayList.iterator();
        int index = 0;
        while (iterator.hasNext() && iterator2.hasNext()) {
            String a = iterator.next();
            String b = iterator2.next();
            Assertions.assertEquals(a, b);
            index++;
        }
        Assertions.assertEquals(linkedListTests.linkList.size(), index);
        Assertions.assertEquals(arrayListTests.arrayList.size(), index);
    }

    @Order(20)
    @Test
    @Description("Проверка каждого элемента из списка с ожидаемым списком")
    void testForEach() {
        linkedListTests.linkList.forEach(element -> {
            String expectedElement = arrayListTests.arrayList.removeFirst();
            Assertions.assertEquals(element, expectedElement);
        });

    }

    @Order(21)
    @Test
    @Description("Проверка сравнения содержимого целых списков, преобразовывая списки в массив")
    void testToArray() {
        Assertions.assertArrayEquals(linkedListTests.linkList.toArray(), arrayListTests.arrayList.toArray());
    }

    @Order(22)
    @Test
    @Description("Проверка равенств двух массивов, где произведено преобразования Stream в массив определенного типа")
    void testToArrayStream() {
        String[] actual = arrayListTests.arrayList.toArray(String[]::new);
        Assertions.assertEquals(Arrays.toString(linkedListTests.linkList.toArray()), Arrays.toString(actual));
    }

    @Order(23)
    @Test
    @Description("Преобразовали все элементы в верхний регистр и проверили со списком," +
            " где содержатся элементы в верхнем регистре")
    void testReplaceAll() {
        UnaryOperator<String> toUpper = String::toUpperCase;
        linkedListTests.linkList.replaceAll(toUpper);
        arrayListTests.arrayList.replaceAll(toUpper);
        Assertions.assertEquals(linkedListTests.linkList.toString(), arrayListTests.arrayList.toString());
    }

    @Order(24)
    @Test
    @Description("Сортируем список в порядке возрастания и проверяем с ожидаемым списком")
    void testSort() {
        linkedListTests.linkList.sort(Comparator.naturalOrder());
        Assertions.assertEquals(linkedListTests.linkList.toString(), arrayListTests.arrayList.toString());
    }

    @Order(25)
    @Test
    @Description("После удаления элементов в списке проверяем, что размер списка стал 0")
    void testClear() {
        linkedListTests.linkList.clear();
        arrayListTests.arrayList.clear();
        Assertions.assertEquals(linkedListTests.linkList.toString(), arrayListTests.arrayList.toString());
    }

    @Order(26)
    @Test
    @Description("Заменяем элемент по индексу и проверяем список с ожидаемым результатом, произошла ли замена успешно")
    void testSet() {
        linkedListTests.linkList.set(1, "Three_Test");
        arrayListTests.arrayList.set(1, "Three_Test");
        Assertions.assertEquals(linkedListTests.linkList.toString(), arrayListTests.arrayList.toString());
    }

    @Order(27)
    @Test
    @Description("Проверяем индекс первого вхождения элемента, возвращает индекс")
    void testIndexOf() {
        Assertions.assertEquals(linkedListTests.linkList.indexOf("One_Test"), 0);
        Assertions.assertEquals(arrayListTests.arrayList.indexOf("Two_Test"), 1);
    }

    @Order(28)
    @Test
    @Description("Проверяем индекс последнего вхождения элемента, возвращает индекс")
    void testLastIndexOf() {
        linkedListTests.linkList.add("One_Test");
        arrayListTests.arrayList.add("One_Test");
        Assertions.assertEquals(linkedListTests.linkList.lastIndexOf("One_Test"), 2);
        Assertions.assertEquals(arrayListTests.arrayList.lastIndexOf("One_Test"), 2);

    }

    @Order(30)
    @Test
    @Description("Проверка на корректность работы итерированности по элементам списка по индексу в обоих направлениях (вперёд и назад)," +
            "а также модифицировать элементы в процессе итерации")
    void testListIteratorIndex() {
        ListIterator<String> iterator = linkedListTests.linkList.listIterator(0);
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

        ListIterator<String> iterator2 = arrayListTests.arrayList.listIterator(0);

        Assertions.assertTrue(iterator2.hasNext());
        Assertions.assertEquals("One_Test", iterator2.next());

        Assertions.assertTrue(iterator2.hasNext());
        Assertions.assertEquals("Two_Test", iterator2.next());

        Assertions.assertTrue(iterator2.hasPrevious());
        Assertions.assertEquals("Two_Test", iterator2.previous());

        Assertions.assertTrue(iterator2.hasPrevious());
        Assertions.assertEquals("One_Test", iterator2.previous());
    }


    @Order(31)
    @Test
    @Description("Проверка на корректность работы итерированности по элементам списка в обоих направлениях (вперёд и назад)," +
            "а также модифицировать элементы в процессе итерации")
    void testListIterator() {
        testAdd();
        ListIterator<String> iterator = linkedListTests.linkList.listIterator(0);

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

        ListIterator<String> iterator2 = arrayListTests.arrayList.listIterator(0);
        Assertions.assertTrue(iterator2.hasNext());
        Assertions.assertEquals("One_Test", iterator2.next());

        Assertions.assertTrue(iterator2.hasNext());
        Assertions.assertEquals("Two_Test", iterator2.next());

        // Проверяем обратное направление итерации
        Assertions.assertTrue(iterator2.hasPrevious());
        Assertions.assertEquals("Two_Test", iterator2.previous());

        Assertions.assertTrue(iterator2.hasPrevious());
        Assertions.assertEquals("One_Test", iterator2.previous());
    }

    @Order(32)
    @Test
    @Description("Получаем подсписок от индекса 1 до 3 и" +
            "проверяем, что подсписок соответствует ожидаемым значениям")
    void testSubList() {
        Assertions.assertEquals(linkedListTests.linkList.subList(0, 1).toString(),
                arrayListTests.arrayList.subList(0, 1).toString());

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
        Spliterator<String> spliterator = linkedListTests.linkList.spliterator();
        spliterator.forEachRemaining(arrayListTests.arrayList::add);
        Assertions.assertEquals(arrayListTests.arrayList.toString(), "[One_Test, Two_Test, One_Test, Two_Test]");
    }

    @Order(34)
    @Test
    @Description("Фильтрация по длине строки, и проверка, что результат содержит элементы, удовлетворяющие условию")
    void testStream() {
        // stream() для фильтрации элементов и сбора в новый список
        List<String> linkList = linkedListTests.linkList.stream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toList());

        List<String> arrayList = arrayListTests.arrayList.stream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toList());

        Assertions.assertEquals(linkList, arrayList);
    }

    @Order(35)
    @Test
    @Description("Параллельно выполняется фильтрация элементов, и результат сравнивается с ожидаемым значением")
    void testParallelStream() {
        List<String> linkList = linkedListTests.linkList.parallelStream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toList());

        List<String> arrayList = arrayListTests.arrayList.parallelStream()
                .filter(s -> s.length() > 8)
                .collect(Collectors.toList());

        Assertions.assertEquals(linkList, arrayList);
    }

    @Order(36)
    @Test
    @Description("Проверяем, что список в обратном порядке")
    void testRevers() {
        // Инвертируем порядок элементов в исходном списке
        Collections.reverse(linkedListTests.linkList);
        Collections.reverse(arrayListTests.arrayList);
        Assertions.assertEquals(linkedListTests.linkList.toString(), arrayListTests.arrayList.toString());
    }

    @AfterEach
    void display() {
        System.out.println(arrayListTests.arrayList.toString() + "\n"
                + linkedListTests.linkList.toString() + "\n");
    }
}
