package MapTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Set;

public class ComparisonMapsTests {
    HashMapTests hashMapTests = new HashMapTests();
    LinkedHashMapTests linkedHashMapTests = new LinkedHashMapTests();

    @BeforeEach
    void add() {
        hashMapTests.testPut();
        linkedHashMapTests.testPut();
    }

    @Order(1)
    @Test
    @Description("Проверка двух мапов на их одиноковое содержимое")
    void testPut() {
        Assertions.assertEquals(hashMapTests.hashMap, linkedHashMapTests.linkedHashMapTests);
    }

    @Order(2)
    @Test
    @Description("Добавление всех элементов из одной карты в другую")
    void testPutAll() {
        hashMapTests.hashMap.putAll(linkedHashMapTests.linkedHashMapTests);
        Assertions.assertEquals(hashMapTests.hashMap.toString(), "{1=One, 2=Two, 3=Three}");
    }

    @Order(3)
    @Test
    @Description("Если ключ 1 отсутствует в карте, метод вставляет его со значением key1 и возвращает null" +
            "проверяем, что метод вернул null")
    void testPutIfAbsent() {
        String result1 = hashMapTests.hashMap.putIfAbsent(1, "key1");
        String result2 = linkedHashMapTests.linkedHashMapTests.putIfAbsent(1, "key1");
        Assertions.assertEquals(result2, result1);
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(1), hashMapTests.hashMap.get(1));
    }

    @Order(4)
    @Test
    @Description("Проверяем размер карты")
    void testSize() {
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.size(), hashMapTests.hashMap.size());
    }

    @Order(5)
    @Test
    @Description("Проверяем, что карта не пуста")
    void testIsEmpty() {
        Assertions.assertEquals(hashMapTests.hashMap.isEmpty(), linkedHashMapTests.linkedHashMapTests.isEmpty());
    }

    @Order(6)
    @Test
    @Description("Проверяем, что карта не содержит ключ 5 и содержит ключ 1")
    void testContainsKey() {
        testPut();
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.containsKey(5), hashMapTests.hashMap.containsKey(5));
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.containsKey(1), hashMapTests.hashMap.containsKey(1));
    }

    @Order(7)
    @Test
    @Description("Проверяем, что значение присутствует")
    void testContainsValue() {
        Assertions.assertEquals(hashMapTests.hashMap.containsValue("One"), linkedHashMapTests.linkedHashMapTests.containsValue("One"));
    }

    @Order(8)
    @Test
    @Description("Поиск значения")
    void testGet() {
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(3), hashMapTests.hashMap.get(3));
    }

    @Order(9)
    @Test
    @Description("Удаление элемента в мапе")
    void testRemove() {
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.remove(3), hashMapTests.hashMap.remove(3));

    }

    @Order(10)
    @Test
    @Description("Проверяем, что карта пустая")
    void testСlear() {
        linkedHashMapTests.linkedHashMapTests.clear();
        hashMapTests.hashMap.clear();
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.isEmpty(), hashMapTests.hashMap.isEmpty());
    }

    @Order(11)
    @Test
    @Description("Проверяем, что keySet содержит все ключи и " +
            "Проверяем, что несуществующий ключ отсутствует")
    void testKeySet() {
        // Получаем множество ключей
        Set<Integer> keySet = hashMapTests.hashMap.keySet();
        Set<Integer> keySet1 = linkedHashMapTests.linkedHashMapTests.keySet();
        Assertions.assertEquals(keySet.contains(1), keySet1.contains(1));
        Assertions.assertEquals(keySet.contains(2), keySet1.contains(2));
        Assertions.assertEquals(keySet.contains(3), keySet1.contains(3));
    }

    @Order(12)
    @Test
    @Description("Проверяем, что значения присутствуют и проверяем, что несуществующее значение отсутствует ")
    void testValues() {
        Collection<String> values = hashMapTests.hashMap.values();
        Collection<String> values1 = linkedHashMapTests.linkedHashMapTests.values();
        Assertions.assertEquals(values.contains("One"), values1.contains("One"));
        Assertions.assertEquals(values.contains("Two"), values1.contains("Two"));
        Assertions.assertEquals(values.contains("Three"), values1.contains("Three"));
        Assertions.assertEquals(values.contains("Fourth"), values1.contains("Fourth"));
    }

    @Order(13)
    @Test
    @Description("Проверяем, что значение совпадает")
    void testGetOrDefault() {
        // Получаем значение для существующего ключа
        String result1 = hashMapTests.hashMap.getOrDefault(1, "One");
        String result2 = linkedHashMapTests.linkedHashMapTests.getOrDefault(1, "One");
        Assertions.assertEquals(result2, result1);
    }

    @Order(14)
    @Test
    @Description("Проверяем, что все значения преобразованы в верхний регистр")
    void testReplaceAll() {
        hashMapTests.hashMap.replaceAll((key, value) -> value.toUpperCase());
        linkedHashMapTests.linkedHashMapTests.replaceAll((key, value) -> value.toUpperCase());
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(1), hashMapTests.hashMap.get(1));
    }

    @Order(15)
    @Test
    @Description("Проверяем, что возвращенное старое значение корректное," +
            "Проверяем, что для несуществующего ключа возвращается null")
    void testReplace() {
        String oldValue = hashMapTests.hashMap.replace(2, "TEST");
        String oldValue1 = linkedHashMapTests.linkedHashMapTests.replace(2, "TEST");

        Assertions.assertEquals(oldValue1, oldValue);
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(2), hashMapTests.hashMap.get(2));

        String nonExistentValue = hashMapTests.hashMap.replace(4, "Wow");
        String nonExistentValue1 = linkedHashMapTests.linkedHashMapTests.replace(4, "Wow");

        Assertions.assertEquals(nonExistentValue1, nonExistentValue);
    }

    @Order(16)
    @Test
    @Description("Проверяем, что значение было добавлено по ключу")
    void testComputeIfAbsent() {
        String value = hashMapTests.hashMap.computeIfAbsent(1, key -> "One" + key);
        String value1 = linkedHashMapTests.linkedHashMapTests.computeIfAbsent(1, key -> "One" + key);

        Assertions.assertEquals(value, value1);
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(1), hashMapTests.hashMap.get(1));
    }

    @Order(17)
    @Test
    @Description("Проверяем, что значение было добавлено по ключу," +
            "Используем compute для изменения значения по ключу 3 (не существующему)")
    void testCompute() {
        String result = hashMapTests.hashMap.compute(1, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        String result1 = linkedHashMapTests.linkedHashMapTests.compute(1, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        Assertions.assertEquals(result, result1);
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(1), hashMapTests.hashMap.get(1));

        String result2 = hashMapTests.hashMap.compute(3, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        String result3 = linkedHashMapTests.linkedHashMapTests.compute(3, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        Assertions.assertEquals(result3, result2);
    }

    @Order(18)
    @Test
    @Description("Используем метод merge для изменения значения по ключу 1 и" +
            " Проверяем, что значение по ключу 1 изменилось")
    void testMerge() {
        String result = hashMapTests.hashMap.merge(1, " ONE", (oldValue, newValue) -> oldValue + newValue);
        String result1 = linkedHashMapTests.linkedHashMapTests.merge(1, " ONE", (oldValue, newValue) -> oldValue + newValue);

        Assertions.assertEquals(result, result1);
        Assertions.assertEquals(linkedHashMapTests.linkedHashMapTests.get(1), hashMapTests.hashMap.get(1));
    }

    @AfterEach
    void display() {
        System.out.println(hashMapTests.hashMap.toString() + "\n"
                + linkedHashMapTests.linkedHashMapTests.toString() + "\n");
    }
}
