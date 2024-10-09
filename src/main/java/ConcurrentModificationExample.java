import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("Element: " + element);

            // Модификация списка во время итерации вызовет ConcurrentModificationException
            if (element.equals("Two")) {
//                // Попытка удалить элемент напрямую
//                list.remove(element);
                // Удаление через итератор
                iterator.remove();

            }
        }
    }
}