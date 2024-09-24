import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MyMap implements Map {
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
        return Map.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer action) {
        Map.super.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction function) {
        Map.super.replaceAll(function);
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return Map.super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return Map.super.remove(key, value);
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        return Map.super.replace(key, oldValue, newValue);
    }

    @Override
    public Object replace(Object key, Object value) {
        return Map.super.replace(key, value);
    }

    @Override
    public Object computeIfAbsent(Object key, Function mappingFunction) {
        return Map.super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public Object computeIfPresent(Object key, BiFunction remappingFunction) {
        return Map.super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public Object compute(Object key, BiFunction remappingFunction) {
        return Map.super.compute(key, remappingFunction);
    }

    @Override
    public Object merge(Object key, Object value, BiFunction remappingFunction) {
        return Map.super.merge(key, value, remappingFunction);
    }

}
