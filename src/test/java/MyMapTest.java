import org.junit.jupiter.api.Test;

public class MyMapTest {
    MyMap<String, Integer> myMap = new MyMap<>();

    MyMap<String, Integer> myMap2 = new MyMap<>();

    @Test
    void testMapComparison() {
        myMap.put("one", 1);
        myMap.put("two", 2);
        myMap.put("three", 3);

        myMap2.put("one", 1);
        myMap2.put("two", 2);
        myMap2.put("three", 3);

        System.out.println("Map1 equals Map2: " + myMap.equals(myMap2));
        System.out.println("Map1 hashCode: " + myMap.hashCode());
        System.out.println("Map2 hashCode: " + myMap2.hashCode());
    }

    @Test
    void testMap() {
        myMap.put("one", 1);
        myMap.put("two", 2);
        myMap.put("three", 3);

        System.out.println("Keys: " + myMap.keySet());
        System.out.println("Values: " + myMap.values());
        System.out.println("Entries: " + myMap.entrySet());
        System.out.println("Size: " + myMap.size());
        System.out.println("containsKey: " + myMap.containsKey("one"));
        System.out.println("containsValue: " + myMap.containsValue(1));
        System.out.println(myMap.get("two"));
        System.out.println("Put: " + myMap.put("three", 3));
        System.out.println("resize: " + myMap.remove("two"));

        System.out.println("Get 'two' or default: " + myMap.getOrDefault("two", -1));
        System.out.println("Get 'four' or default: " + myMap.getOrDefault("four", -1));

        System.out.println("Entries:");
        myMap.forEach((k, v) -> System.out.println(k + ": " + v));

        myMap.remove("two", 2);
        System.out.println("After removing 'two': " + myMap.entrySet());

        myMap.putIfAbsent("three", 5);
        myMap.putIfAbsent("four", 4);
        System.out.println("After putIfAbsent(): " + myMap.entrySet());

        myMap.replace("three", 3, 33);
        System.out.println("After replacing 'three' with 33: " + myMap.entrySet());

        //computeIfAbsent()
        System.out.println("Compute if absent 'four': " + myMap.computeIfAbsent("four", k -> 4));
        System.out.println("After computeIfAbsent(): " + myMap.entrySet());

        System.out.println("Compute if present 'two': " + myMap.computeIfPresent("two", (k, v) -> v * 2));
        System.out.println("After computeIfPresent(): " + myMap.entrySet());

        System.out.println("Compute 'three': " + myMap.compute("three", (k, v) -> v != null ? v + 1 : 100));
        System.out.println("Compute 'five' (new entry): " + myMap.compute("five", (k, v) -> v != null ? v + 1 : 5));
        System.out.println("After compute(): " + myMap.entrySet());

        System.out.println("Merge 'one' with 10: " + myMap.merge("one", 10, (v1, v2) -> v1 + v2));
        System.out.println("Merge 'six' with 6 (new entry): " + myMap.merge("six", 6, (v1, v2) -> v1 + v2));
        System.out.println("After merge(): " + myMap.entrySet());
    }
}
