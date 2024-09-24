import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Benchmark {
    public static void main(String[] args) {
        // Размер тестового списка
        int size = 100000;

        // Создаем списки
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        try {
            FileWriter writer = new FileWriter("benchmark_results.txt");

            // Измеряем время для ArrayList добавление элемента
            for (int i = 0; i < size; i++) {
                arrayList.add(i);
            }
            long startTime = System.nanoTime();
            long endTime = System.nanoTime();
            long arrayListAddTime = endTime - startTime;
            writer.write("ArrayList add(): " + arrayListAddTime + " ns\n");

            // Измеряем время для LinkedList добавление элемента
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.add(i);
            }
            endTime = System.nanoTime();
            long linkedListAddTime = endTime - startTime;
            writer.write("LinkedList add(): " + linkedListAddTime + " ns\n");

            // Измеряем время для LinkedList добавление элемента
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.add(i);
            }
            linkedList.add(3, 9);
            endTime = System.nanoTime();
            long linkedListAddIndTime = endTime - startTime;
            writer.write("LinkedList add(index): " + linkedListAddIndTime + " ns\n");

            // Измеряем время для ArrayList добавление элемента по индексу
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayList.add(i);
            }
            arrayList.add(3, 9);
            endTime = System.nanoTime();
            long arrayListAddIndTime = endTime - startTime;
            writer.write("ArrayList add(index): " + arrayListAddIndTime + " ns\n");

            // Измеряем время для LinkedList добавление элемента в нулевую позицию списка
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.addFirst(i);
            }
            endTime = System.nanoTime();
            long linkedListAddFirstTime = endTime - startTime;
            writer.write("LinkedList addFirst(): " + linkedListAddFirstTime + " ns\n");

            // Измеряем время для ArrayList добавление элемента в нулевую позицию списка
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayList.addFirst(i);
            }
            endTime = System.nanoTime();
            long arrayListAddFirstTime = endTime - startTime;
            writer.write("ArrayList addFirst(): " + arrayListAddFirstTime + " ns\n");

            // Измеряем время для LinkedList добавление элемента в нулевую позицию списка
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.addLast(i);
            }
            endTime = System.nanoTime();
            long linkedListAddLastTime = endTime - startTime;
            writer.write("LinkedList addLast(): " + linkedListAddLastTime + " ns\n");

            // Измеряем время для ArrayList добавление элемента в конец списка
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayList.addLast(i);
            }
            endTime = System.nanoTime();
            long arrayListAddLastTime = endTime - startTime;
            writer.write("ArrayList addLast(): " + arrayListAddLastTime + " ns\n");

            // Измеряем время для LinkedList добавление элемента в конец списка
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.add(i);
            }
            endTime = System.nanoTime();
            linkedListAddTime = endTime - startTime;
            writer.write("LinkedList add(): " + linkedListAddTime + " ns\n");

            // Измеряем время доступа по индексу для ArrayList
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayList.get(i);
            }
            endTime = System.nanoTime();
            long arrayListGetTime = endTime - startTime;
            writer.write("ArrayList get(): " + arrayListGetTime + " ns\n");

            // Измеряем время доступа по индексу для LinkedList
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedList.get(i);
            }
            endTime = System.nanoTime();
            long linkedListGetTime = endTime - startTime;
            writer.write("LinkedList get(): " + linkedListGetTime + " ns\n");

            // Измеряем время для ArrayList удаление нулевого элемента
            startTime = System.nanoTime();
            for (int i = size - 1; i >= 0; i--) {
                arrayList.removeFirst();
            }
            endTime = System.nanoTime();
            long arrayListRemoveFirstTime = endTime - startTime;
            writer.write("ArrayList removeFirst(): " + arrayListRemoveFirstTime + " ns\n");

            // Измеряем время для LinkedList удаление нулевого элемента
            startTime = System.nanoTime();
            for (int i = size - 1; i >= 0; i--) {
                linkedList.removeFirst();
            }
            endTime = System.nanoTime();
            long linkedListRemoveFirstTime = endTime - startTime;
            writer.write("LinkedList removeFirst(): " + linkedListRemoveFirstTime + " ns\n");

            // Измеряем время для ArrayList удаление последнего элемента
            startTime = System.nanoTime();
            for (int i = size - 1; i >= 0; i--) {
                arrayList.removeLast();
            }
            endTime = System.nanoTime();
            long arrayListRemoveLastTime = endTime - startTime;
            writer.write("ArrayList removeLast(): " + arrayListRemoveLastTime + " ns\n");

            // Измеряем время для LinkedList удаление последнего элемента
            startTime = System.nanoTime();
            for (int i = size - 1; i >= 0; i--) {
                linkedList.removeLast();
            }
            endTime = System.nanoTime();
            long linkedListRemoveLastTime = endTime - startTime;
            writer.write("LinkedList removeLast(): " + linkedListRemoveLastTime + " ns\n");

            // Измеряем время для ArrayList удаление
            startTime = System.nanoTime();
            for (int i = size - 1; i >= 0; i--) {
                arrayList.remove(i);
            }
            endTime = System.nanoTime();
            long arrayListRemoveTime = endTime - startTime;
            writer.write("ArrayList remove(): " + arrayListRemoveTime + " ns\n");

            // Измеряем время для LinkedList удаление
            startTime = System.nanoTime();
            for (int i = size - 1; i >= 0; i--) {
                linkedList.remove(i);
            }
            endTime = System.nanoTime();
            long linkedListRemoveTime = endTime - startTime;
            writer.write("LinkedList remove(): " + linkedListRemoveTime + " ns\n");

            writer.close();
            System.out.println("Benchmark completed and results saved to benchmark_results.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

