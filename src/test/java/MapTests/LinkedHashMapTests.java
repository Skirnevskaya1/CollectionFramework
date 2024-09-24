package MapTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapTests {
    LinkedHashMap<Integer, String> linkedHashMapTests = new LinkedHashMap<>();
    LinkedHashMap<Integer, String> linkedHashMapTests1 = new LinkedHashMap<>();

    @Order(1)
    @Test
    @Description("Добавление элемента в список")
    void testPut() {
        linkedHashMapTests.put(1, "One");
        linkedHashMapTests.put(2, "Two");
        linkedHashMapTests.put(3, "Three");
        Assertions.assertEquals(linkedHashMapTests.toString(), "{1=One, 2=Two, 3=Three}");
    }

    @Order(2)
    @Test
    @Description("Добавление всех элементов из одной карты в другую")
    void testPutAll() {
        testPut();
        linkedHashMapTests.putAll(linkedHashMapTests1);
        Assertions.assertEquals(linkedHashMapTests.toString(), "{1=One, 2=Two, 3=Three}");
    }

    @Order(3)
    @Test
    @Description("Если ключ 1 отсутствует в карте, метод вставляет его со значением key1 и возвращает null" +
            "проверяем, что метод вернул null")
    void testPutIfAbsent() {
        String result1 = linkedHashMapTests.putIfAbsent(1, "key1");
        Assertions.assertEquals(null, result1);
        Assertions.assertEquals("key1", linkedHashMapTests.get(1));
    }

    @Order(4)
    @Test
    @Description("Проверяем размер карты")
    void testSize() {
        testPut();
        Assertions.assertEquals(3, linkedHashMapTests.size());
    }

    @Order(5)
    @Test
    @Description("Проверяем, что карта не пуста")
    void testIsEmpty() {
        testPut();
        Assertions.assertEquals(false, linkedHashMapTests.isEmpty());
    }

    @Order(6)
    @Test
    @Description("Проверяем, что карта не содержит ключ 5 и содержит ключ 1")
    void testContainsKey() {
        testPut();
        Assertions.assertEquals(false, linkedHashMapTests.containsKey(5));
        Assertions.assertEquals(true, linkedHashMapTests.containsKey(1));
    }

    @Order(7)
    @Test
    @Description("Проверяем, что значение присутствует")
    void testContainsValue() {
        testPut();
        Assertions.assertEquals(true, linkedHashMapTests.containsValue("One"));
    }

    @Order(8)
    @Test
    @Description("Поиск значения")
    void testGet() {
        testPut();
        Assertions.assertEquals("Three", linkedHashMapTests.get(3));
    }

    @Order(9)
    @Test
    @Description("Удаление элемента в мапе")
    void testRemove() {
        testPut();
        Assertions.assertEquals("Three", linkedHashMapTests.remove(3));
    }

    @Order(10)
    @Test
    @Description("Проверяем, что карта пустая")
    void testСlear() {
        testPut();
        linkedHashMapTests.clear();
        Assertions.assertEquals(true, linkedHashMapTests.isEmpty());
    }

    @Order(11)
    @Test
    @Description("Проверяем, что keySet содержит все ключи и " +
            "Проверяем, что несуществующий ключ отсутствует")
    void testKeySet() {
        testPut();
        // Получаем множество ключей
        Set<Integer> keySet = linkedHashMapTests.keySet();
        // Проверяем, что keySet содержит все ключи
        Assertions.assertEquals(3, keySet.size());
        Assertions.assertTrue(keySet.contains(1));
        Assertions.assertTrue(keySet.contains(2));
        Assertions.assertTrue(keySet.contains(3));
        Assertions.assertFalse(keySet.contains(4));
    }

    @Order(12)
    @Test
    @Description("Проверяем, что значения присутствуют и проверяем, что несуществующее значение отсутствует ")
    void testValues() {
        testPut();
        Collection<String> values = linkedHashMapTests.values();
        Assertions.assertTrue(values.contains("One"));
        Assertions.assertTrue(values.contains("Two"));
        Assertions.assertTrue(values.contains("Three"));
        Assertions.assertFalse(values.contains("Fourth"));
    }

    @Order(13)
    @Test
    @Description("Проверяем, что все ожидаемые пары найдены")
    void testEntrySet() {
        testPut();
        // Получаем набор пар "ключ-значение"
        Set<Map.Entry<Integer, String>> entrySet = linkedHashMapTests.entrySet();
        // Проверяем, что набор содержит ожидаемые пары
        boolean foundFirst = false;
        boolean foundSecond = false;
        boolean foundThird = false;

        for (Map.Entry<Integer, String> entry : entrySet) {
            if (entry.getKey() == 1 && entry.getValue().equals("One")) {
                foundFirst = true;
            }
            if (entry.getKey() == 2 && entry.getValue().equals("Two")) {
                foundSecond = true;
            }
            if (entry.getKey() == 3 && entry.getValue().equals("Three")) {
                foundThird = true;
            }
        }
        Assertions.assertTrue(foundFirst);
        Assertions.assertTrue(foundSecond);
        Assertions.assertTrue(foundThird);
    }

    @Order(14)
    @Test
    @Description("Проверяем, что значение совпадает")
    void testGetOrDefault() {
        testPut();
        // Получаем значение для существующего ключа
        String result1 = linkedHashMapTests.getOrDefault(1, "One");
        Assertions.assertEquals("One", result1);
        // Проверяем, что значение совпадает

        // Получаем значение для несуществующего ключа
        String result2 = linkedHashMapTests.getOrDefault(4, "Default Value");
        Assertions.assertEquals("Default Value", result2);
        // Проверяем, что возвращается значение по умолчанию

    }

    @Order(15)
    @Test
    @Description("Ожидаем, что строка будет содержать все ключи и значения")
    void testForEach() {
        testPut();
        // Используем переменные для проверки значений
        StringBuilder result = new StringBuilder();
        // Используем forEach для обхода карты и сбора значений
        linkedHashMapTests.forEach((key, value) -> {
            result.append(key).append(": ").append(value).append(", ");
        });
        // Удаляем последнюю запятую и пробел
        if (!result.isEmpty()) {
            result.setLength(result.length() - 2);
        }
        String expectedResult = "1: One, 2: Two, 3: Three";
        Assertions.assertEquals(expectedResult, result.toString());
    }

    @Order(16)
    @Test
    @Description("Проверяем, что все значения преобразованы в верхний регистр")
    void testReplaceAll() {
        testPut();
        linkedHashMapTests.replaceAll((key, value) -> value.toUpperCase());
        Assertions.assertEquals("ONE", linkedHashMapTests.get(1));
    }

    @Order(17)
    @Test
    @Description("Проверяем, что возвращенное старое значение корректное," +
            "Проверяем, что для несуществующего ключа возвращается null")
    void testReplace() {
        testPut();
        String oldValue = linkedHashMapTests.replace(2, "TEST");
        Assertions.assertEquals("Two", oldValue);
        Assertions.assertEquals("TEST", linkedHashMapTests.get(2));
        String nonExistentValue = linkedHashMapTests.replace(4, "Wow");
        Assertions.assertEquals(null, nonExistentValue);
    }

    @Order(18)
    @Test
    @Description("Проверяем, что значение было добавлено по ключу")
    void testComputeIfAbsent() {
        testPut();
        String value1 = linkedHashMapTests.computeIfAbsent(1, key -> "One" + key);
        Assertions.assertEquals("One", value1);
        Assertions.assertEquals("One", linkedHashMapTests.get(1));
    }

    @Order(19)
    @Test
    @Description("Проверяем, что значение было добавлено по ключу," +
            "Используем compute для изменения значения по ключу 3 (не существующему)")
    void testCompute() {
        testPut();
        String result1 = linkedHashMapTests.compute(1, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        Assertions.assertEquals("ONE", result1);
        Assertions.assertEquals("ONE", linkedHashMapTests.get(1));
        String result2 = linkedHashMapTests.compute(3, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        // Проверяем, что значение для несуществующего ключа добавлено
        Assertions.assertEquals("THREE", result2);
    }

    @Order(20)
    @Test
    @Description("Используем метод merge для изменения значения по ключу 1 и" +
            " Проверяем, что значение по ключу 1 изменилось")
    void testMerge() {
        testPut();
        String result1 = linkedHashMapTests.merge(1, " ONE", (oldValue, newValue) -> oldValue + newValue);
        Assertions.assertEquals("One ONE", result1);
        Assertions.assertEquals("One ONE", linkedHashMapTests.get(1));
    }

    @AfterEach
    void display() {
        System.out.println(linkedHashMapTests.toString());
    }
}
