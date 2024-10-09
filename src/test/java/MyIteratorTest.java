import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MyIteratorTest {

    Integer[] numbers = {1, 2, 3, 4, 5};
    MyIterator<Integer> iterator = new MyIterator<>(numbers);

    @Test
    void testIterator() {
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println("Next: " + number);
        }

        iterator = new MyIterator<>(numbers);
        iterator.next();  // пропускаем первый элемент
        iterator.remove();  // удаляем первый элемент
        System.out.println("После удаления: " + Arrays.toString(numbers));

        iterator = new MyIterator<>(numbers);
        iterator.forEachRemaining(n -> System.out.println("forEachRemaining: " + n));
    }
}
