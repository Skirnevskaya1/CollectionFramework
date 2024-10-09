import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MyMap<K, V> implements Map<K, V> {

    private Object[] keys;
    private Object[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public MyMap() {
        keys = new Object[10];
        values = new Object[10];
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
    public boolean containsKey(Object key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V oldValue = (V) values[i];
                values[i] = value;
                return oldValue;
            }
        }
        if (size == keys.length) {
            resize();
        }
        keys[size] = key;
        values[size] = value;
        size++;
        return null;
    }

    private void resize() {
        int newCapacity = keys.length * 2;
        keys = Arrays.copyOf(keys, newCapacity);
        values = Arrays.copyOf(values, newCapacity);
    }

    @Override
    public V remove(Object key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V oldValue = (V) values[i];
                System.arraycopy(keys, i + 1, keys, i, size - i - 1);
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                size--;
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(keys, null);
        Arrays.fill(values, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            keySet.add((K) keys[i]);
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> valueList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            valueList.add((V) values[i]);
        }
        return valueList;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            final K key = (K) keys[i];
            final V value = (V) values[i];
            entrySet.add(new AbstractMap.SimpleEntry<>(key, value));
        }
        return entrySet;
    }

    // возвращает значение для заданного ключа
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return Map.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (int i = 0; i < size; i++) {
            action.accept((K) keys[i], (V) values[i]);
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Map.super.replaceAll(function);
    }

    // вставляет пару ключ-значение в карту только, если указанный ключ ещё не связан со значением
    @Override
    public V putIfAbsent(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        put(key, value);
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key) && values[i].equals(value)) {
                System.arraycopy(keys, i + 1, keys, i, size - i - 1);
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key) && values[i].equals(oldValue)) {
                values[i] = newValue;
                return true;
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V oldValue = (V) values[i];
                values[i] = value;
                return oldValue;
            }
        }
        return null;
    }

    //Если ключ отсутствует, этот метод вычисляет значение с помощью переданной функции
    // и вставляет пару ключ-значение в карту
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        V newValue = mappingFunction.apply(key);
        if (newValue != null) {
            put(key, newValue);
        }
        return newValue;
    }

    //Этот метод применяет переданную функцию, если ключ присутствует, и обновляет значение для ключа
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V newValue = remappingFunction.apply(key, (V) values[i]);
                if (newValue == null) {
                    remove(key);
                } else {
                    values[i] = newValue;
                }
                return newValue;
            }
        }
        return null;
    }

    //Если ключ присутствует, вычисляет новое значение, обновляет его или удаляет (если результат null)
    // Если ключ отсутствует, создает новую запись.
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V newValue = remappingFunction.apply(key, (V) values[i]);
                if (newValue == null) {
                    remove(key);
                } else {
                    values[i] = newValue;
                }
                return newValue;
            }
        }
        V newValue = remappingFunction.apply(key, null);
        if (newValue != null) {
            put(key, newValue);
        }
        return newValue;
    }

    //Если ключ существует, применяет переданную функцию для объединения старого и нового значений
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                V newValue = remappingFunction.apply((V) values[i], value);
                if (newValue == null) {
                    remove(key);
                } else {
                    values[i] = newValue;
                }
                return newValue;
            }
        }
        put(key, value);
        return value;
    }
}
