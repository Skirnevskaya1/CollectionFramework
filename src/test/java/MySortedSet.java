import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MySortedSet implements SortedSet {
    @Override
    public Comparator comparator() {
        return null;
    }

    @Override
    public SortedSet subSet(Object fromElement, Object toElement) {
        return null;
    }

    @Override
    public SortedSet headSet(Object toElement) {
        return null;
    }

    @Override
    public SortedSet tailSet(Object fromElement) {
        return null;
    }

    @Override
    public Object first() {
        return null;
    }

    @Override
    public Object last() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        SortedSet.super.forEach(action);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(IntFunction generator) {
        return SortedSet.super.toArray(generator);
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return SortedSet.super.removeIf(filter);
    }

    @Override
    public void clear() {

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

    @Override
    public Spliterator spliterator() {
        return SortedSet.super.spliterator();
    }

    @Override
    public Stream stream() {
        return SortedSet.super.stream();
    }

    @Override
    public Stream parallelStream() {
        return SortedSet.super.parallelStream();
    }

    @Override
    public void addFirst(Object o) {
        SortedSet.super.addFirst(o);
    }

    @Override
    public void addLast(Object o) {
        SortedSet.super.addLast(o);
    }

    @Override
    public Object getFirst() {
        return SortedSet.super.getFirst();
    }

    @Override
    public Object getLast() {
        return SortedSet.super.getLast();
    }

    @Override
    public Object removeFirst() {
        return SortedSet.super.removeFirst();
    }

    @Override
    public Object removeLast() {
        return SortedSet.super.removeLast();
    }

    @Override
    public SortedSet reversed() {
        return SortedSet.super.reversed();
    }
}
