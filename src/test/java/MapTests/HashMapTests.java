package MapTests;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HashMapTests {
    HashMap<Integer, String> hashMap = new HashMap<>();
    HashMap<Integer, String> hashMap1 = new HashMap<>();

    @Order(1)
    @Test
    @Description("Добавление элемента в список")
    void testPut() {
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        Assertions.assertEquals(hashMap.toString(), "{1=One, 2=Two, 3=Three}");
    }

    @Order(2)
    @Test
    @Description("Добавление всех элементов из одной карты в другую")
    void testPutAll() {
        testPut();
        hashMap.putAll(hashMap1);
        Assertions.assertEquals(hashMap.toString(), "{1=One, 2=Two, 3=Three}");
    }

    @Order(3)
    @Test
    @Description("Если ключ 1 отсутствует в карте, метод вставляет его со значением key1 и возвращает null" +
            "проверяем, что метод вернул null")
    void testPutIfAbsent() {
        String result1 = hashMap.putIfAbsent(1, "key1");
        Assertions.assertEquals(null, result1);
        Assertions.assertEquals("key1", hashMap.get(1));
    }

    @Order(4)
    @Test
    @Description("Проверяем размер карты")
    void testSize() {
        testPut();
        Assertions.assertEquals(3, hashMap.size());
    }

    @Order(5)
    @Test
    @Description("Проверяем, что карта не пуста")
    void testIsEmpty() {
        testPut();
        Assertions.assertEquals(false, hashMap.isEmpty());
    }

    @Order(6)
    @Test
    @Description("Проверяем, что карта не содержит ключ 5 и содержит ключ 1")
    void testContainsKey() {
        testPut();
        Assertions.assertEquals(false, hashMap.containsKey(5));
        Assertions.assertEquals(true, hashMap.containsKey(1));
    }

    @Order(7)
    @Test
    @Description("Проверяем, что значение присутствует")
    void testContainsValue() {
        testPut();
        Assertions.assertEquals(true, hashMap.containsValue("One"));
    }

    @Order(8)
    @Test
    @Description("Поиск значения")
    void testGet() {
        testPut();
        Assertions.assertEquals("Three", hashMap.get(3));
    }

    @Order(9)
    @Test
    @Description("Удаление элемента в мапе")
    void testRemove() {
        testPut();
        Assertions.assertEquals("Three", hashMap.remove(3));
    }

    @Order(10)
    @Test
    @Description("Проверяем, что карта пустая")
    void testСlear() {
        testPut();
        hashMap.clear();
        Assertions.assertEquals(true, hashMap.isEmpty());
    }

    @Order(11)
    @Test
    @Description("Проверяем, что keySet содержит все ключи и " +
            "Проверяем, что несуществующий ключ отсутствует")
    void testKeySet() {
        testPut();
        // Получаем множество ключей
        Set<Integer> keySet = hashMap.keySet();
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
        Collection<String> values = hashMap.values();
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
        Set<Map.Entry<Integer, String>> entrySet = hashMap.entrySet();
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
        String result1 = hashMap.getOrDefault(1, "One");
        Assertions.assertEquals("One", result1);
        // Проверяем, что значение совпадает

        // Получаем значение для несуществующего ключа
        String result2 = hashMap.getOrDefault(4, "Default Value");
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
        hashMap.forEach((key, value) -> {
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
        hashMap.replaceAll((key, value) -> value.toUpperCase());
        Assertions.assertEquals("ONE", hashMap.get(1));
    }

    @Order(17)
    @Test
    @Description("Проверяем, что возвращенное старое значение корректное," +
            "Проверяем, что для несуществующего ключа возвращается null")
    void testReplace() {
        testPut();
        String oldValue = hashMap.replace(2, "TEST");
        Assertions.assertEquals("Two", oldValue);
        Assertions.assertEquals("TEST", hashMap.get(2));
        String nonExistentValue = hashMap.replace(4, "Wow");
        Assertions.assertEquals(null, nonExistentValue);
    }

    @Order(18)
    @Test
    @Description("Проверяем, что значение было добавлено по ключу")
    void testComputeIfAbsent() {
        testPut();
        String value1 = hashMap.computeIfAbsent(1, key -> "One" + key);
        Assertions.assertEquals("One", value1);
        Assertions.assertEquals("One", hashMap.get(1));
    }

    @Order(19)
    @Test
    @Description("Проверяем, что значение было добавлено по ключу," +
            "Используем compute для изменения значения по ключу 3 (не существующему)")
    void testCompute() {
        testPut();
        String result1 = hashMap.compute(1, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        Assertions.assertEquals("ONE", result1);
        Assertions.assertEquals("ONE", hashMap.get(1));
        String result2 = hashMap.compute(3, (key, value) -> value == null ? "THREE" : value.toUpperCase());
        // Проверяем, что значение для несуществующего ключа добавлено
        Assertions.assertEquals("THREE", result2);
    }

    @Order(20)
    @Test
    @Description("Используем метод merge для изменения значения по ключу 1 и" +
            " Проверяем, что значение по ключу 1 изменилось")
    void testMerge() {
        testPut();
        String result1 = hashMap.merge(1, " ONE", (oldValue, newValue) -> oldValue + newValue);
        Assertions.assertEquals("One ONE", result1);
        Assertions.assertEquals("One ONE", hashMap.get(1));
    }

    @AfterEach
    void display() {
        System.out.println(hashMap.toString());
    }
}