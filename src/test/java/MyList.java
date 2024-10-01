import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyList<T> implements List<T> {

    // Размер списка
    private int size = 0;
    private T[] elements;

    // Начальная вместимость
    @SuppressWarnings("unchecked")
    public MyList() {
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
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
        T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        //Уменьшаем размер, очищаем последний элемент
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    //Для обхода элементов списка
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if ((!hasNext())) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }
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
        return List.super.toArray(generator);
    }

    @Override
    public boolean add(T o) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = o;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
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
            if (Objects.equals(elements[i], o)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] newArray = c.toArray();
        int newLength = newArray.length;
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(newArray, 0, elements, size, newLength);
        size += newLength;
        return newLength > 0;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (filter.test(elements[i])) {
                removeAt(i--); // Удаляем элемент и сдвигаем индекс
                modified = true;
            }
        }
        return modified;
    }

    //Удаление элемента по индексу
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T removedElement = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Обнуляем последний элемент
        return removedElement;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        for (T element : c) {
            add(index++, element);
        }
        return true;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        for (int i = 0; i < size; i++) {
            elements[i] = operator.apply(elements[i]);
        }
    }

    @Override
    public void sort(Comparator c) {
        Arrays.sort(Arrays.copyOf(elements, size), c);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона");
        }
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
        T oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements ListIterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return elements[--currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove() не поддерживается");
        }


        @Override
        public void set(T t) {
            if (currentIndex <= 0) {
                throw new IllegalStateException();
            }
            elements[currentIndex - 1] = t;
        }

        @Override
        public void add(T t) {
            MyList.this.add(currentIndex++, t);
        }
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return elements[--currentIndex];
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }

            @Override
            public void set(T e) {
                if (currentIndex <= 0 || currentIndex > size) {
                    throw new IllegalStateException();
                }
                elements[currentIndex - 1] = e;
            }

            @Override
            public void add(T e) {
                throw new UnsupportedOperationException("add");
            }
        };
    }

    @Override
    public MyList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        MyList<T> subList = new MyList<>();
        subList.addAll(Arrays.asList(elements).subList(fromIndex, toIndex));
        return subList;
    }

    @Override
    public Spliterator<T> spliterator() {
        return Arrays.spliterator(elements, 0, size);
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
    public void addFirst(T o) {
        add(0, o);
    }

    @Override
    public void addLast(T o) {
        add(o);
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст");
        }
        return elements[0];
    }

    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return elements[size - 1];
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T firstElement = elements[0];
        System.arraycopy(elements, 1, elements, 0, size - 1);
        elements[--size] = null;
        return firstElement;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T lastElement = elements[size - 1];
        elements[--size] = null;
        return lastElement;
    }

    // Метод возврата списка в обратном порядке
    @Override
    public MyList<T> reversed() {
        MyList<T> reversedList = new MyList<>();
        for (int i = size - 1; i >= 0; i--) {
            reversedList.add(elements[i]);
        }
        return reversedList;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
        //
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
        //
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
