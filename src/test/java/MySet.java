import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MySet<T> implements Set<T> {

    private int size = 0;
    private T[] elements;

    // Начальная вместимость
    @SuppressWarnings("unchecked")
    public MySet() {
        elements = (T[]) new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < size; i++) {
            action.accept(elements[i]);
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public Object[] toArray(IntFunction generator) {
        return Set.super.toArray(generator);
    }

    @Override
    public boolean add(T o) {
        if (contains(o)) {
            // Не добавляем элемент, если он уже существует
            return false;
        }
        if (size == elements.length) {
            resize();
        }
        elements[size] = o;
        size++;
        return true;
    }

    // Метод для увеличения размера массива
    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                //Заменяем удаляемый элемент последним
                elements[i] = elements[size - 1];
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    public boolean addAll(MySet<? extends T> otherSet) {
        boolean modified = false;
        for (T element : otherSet) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        boolean removed = false;
        for (int i = 0; i < size; i++) {
            if (filter.test(elements[i])) {
                remove(elements[i]);
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliterator(elements, 0, size, Spliterator.DISTINCT);
    }

    @Override
    public Stream<T> stream() {
        return Arrays.stream(elements, 0, size);
    }

    @Override
    public Stream<T> parallelStream() {
        return Arrays.stream(elements, 0, size).parallel();
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public void print() {
        System.out.println(Arrays.toString(Arrays.copyOf(elements, size)));
    }
}
