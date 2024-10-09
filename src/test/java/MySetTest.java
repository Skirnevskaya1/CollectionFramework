import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Spliterator;

public class MySetTest {

    MySet<Integer> mySet = new MySet<>();

    @Test
    void testsSet() {
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        System.out.println(mySet.add(2));
        System.out.println(mySet.contains(2));
        System.out.println(mySet.contains(4));
        mySet.remove(2);
        System.out.println("Size: " + mySet.size());

        System.out.println("Using forEach:");
        mySet.forEach(System.out::println);

        System.out.println("toArray:");
        Object[] array = mySet.toArray();
        System.out.println(Arrays.toString(array));

        // Добавление всех элементов из другого множества
        MySet<Integer> anotherSet = new MySet<>();
        anotherSet.add(3);
        anotherSet.add(4);
        anotherSet.add(5);

        System.out.println("Adding elements from another set:" + mySet.addAll(anotherSet));

        System.out.println("Stream output:");
        mySet.stream().forEach(System.out::println);

        System.out.println("Parallel Stream output:");
        mySet.parallelStream().forEach(System.out::println);

        Spliterator<Integer> spliterator = mySet.spliterator();
        spliterator.forEachRemaining(System.out::println);

        mySet.clear();
        System.out.print("Clearing the set:");
        mySet.print();
    }
}
