import org.junit.jupiter.api.Test;

import java.util.*;

public class MyListTest {

    MyList<String> myList = new MyList<>();

    @Test
    void testsList() {
        myList.add("One");
        myList.add("Two");
        myList.add("Three");
        System.out.println(myList.get(1));
        System.out.println(myList.size());
        System.out.println("Пустой ли список: " + myList.isEmpty());
        myList.forEach(System.out::println);
        myList.replaceAll(String::toUpperCase);
        System.out.println(Arrays.toString(myList.toArray()));
        myList.sort(Comparator.naturalOrder());

        // Использование ListIterator
        ListIterator<String> iterator = myList.listIterator();
        while (iterator.hasNext()) {
            System.out.println("Next: " + iterator.next());
        }
        while (iterator.hasPrevious()) {
            System.out.println("Previous: " + iterator.previous());
        }

        myList.set(1, "Ten");
        System.out.println(myList.get(1));

        myList.remove(2);
    }
}
