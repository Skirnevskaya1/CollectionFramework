import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MySortedMap implements SortedMap {
    @Override
    public Comparator comparator() {
        return null;
    }

    @Override
    public SortedMap subMap(Object fromKey, Object toKey) {
        return null;
    }

    @Override
    public SortedMap headMap(Object toKey) {
        return null;
    }

    @Override
    public SortedMap tailMap(Object fromKey) {
        return null;
    }

    @Override
    public Object firstKey() {
        return null;
    }

    @Override
    public Object lastKey() {
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
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return Set.of();
    }

    @Override
    public Collection values() {
        return List.of();
    }

    @Override
    public Set<Entry> entrySet() {
        return Set.of();
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return SortedMap.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer action) {
        SortedMap.super.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction function) {
        SortedMap.super.replaceAll(function);
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return SortedMap.super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return SortedMap.super.remove(key, value);
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        return SortedMap.super.replace(key, oldValue, newValue);
    }

    @Override
    public Object replace(Object key, Object value) {
        return SortedMap.super.replace(key, value);
    }

    @Override
    public Object computeIfAbsent(Object key, Function mappingFunction) {
        return SortedMap.super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public Object computeIfPresent(Object key, BiFunction remappingFunction) {
        return SortedMap.super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public Object compute(Object key, BiFunction remappingFunction) {
        return SortedMap.super.compute(key, remappingFunction);
    }

    @Override
    public Object merge(Object key, Object value, BiFunction remappingFunction) {
        return SortedMap.super.merge(key, value, remappingFunction);
    }

    @Override
    public Object putFirst(Object o, Object o2) {
        return SortedMap.super.putFirst(o, o2);
    }

    @Override
    public Object putLast(Object o, Object o2) {
        return SortedMap.super.putLast(o, o2);
    }

    @Override
    public SequencedSet sequencedKeySet() {
        return SortedMap.super.sequencedKeySet();
    }

    @Override
    public SequencedCollection sequencedValues() {
        return SortedMap.super.sequencedValues();
    }

    @Override
    public SequencedSet<Entry> sequencedEntrySet() {
        return SortedMap.super.sequencedEntrySet();
    }

    @Override
    public SortedMap reversed() {
        return SortedMap.super.reversed();
    }

    @Override
    public Entry firstEntry() {
        return SortedMap.super.firstEntry();
    }

    @Override
    public Entry lastEntry() {
        return SortedMap.super.lastEntry();
    }

    @Override
    public Entry pollFirstEntry() {
        return SortedMap.super.pollFirstEntry();
    }

    @Override
    public Entry pollLastEntry() {
        return SortedMap.super.pollLastEntry();
    }
}
