package com.kondenko.week6.quiz;

import java.util.*;

public class LinearProbingHashTable<K, V> implements Map<K, V> {

    protected int m;

    private K[] keys;
    private V[] values;

    private int size = 0;

    public LinearProbingHashTable() {
        this.m = 10;
        keys = (K[]) new Object[m];
        values = (V[]) new Object[m];
    }

    public LinearProbingHashTable(int m) {
        this.m = m;
        keys = (K[]) new Object[m];
        values = (V[]) new Object[m];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    private void grow() {
        if (size >= m - 1) {
            m *= 2;
            keys = Arrays.copyOf(keys, m);
            values = Arrays.copyOf(values, m);
        }
    }

    private void shrink() {
        if (size < m / 4) {
            m /= 2;
            keys = Arrays.copyOf(keys, m);
            values = Arrays.copyOf(values, m);
        }
    }

    @Override
    public V put(K key, V value) {
        if (value != null) {
            size++;
            grow();
        } else {
            size--;
            shrink();
        }
        int hash = hash(key);
        for (int i = hash; i < keys.length; i = (i + 1) % m) {
            if (keys[i] == null) {
                keys[i] = key;
                values[i] = value;
                return value;
            }
        }
        throw new IllegalStateException(String.format("Couldn't find a place to insert %s: %s", key, value));
    }

    @Override
    public V get(Object key) {
        int hash = hash((K) key);
        for (int i = hash; i < keys.length; i = (i + 1) % m) {
            if (key.equals(keys[i])) return values[i];
            if (hash == 0 ? i == m - 1 : i == hash - 1) return null;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        return put((K) key, null);
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) return false;
        for (V v : values) {
            if (value.equals(v)) return true;
        }
        return false;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        Arrays.fill(keys, null);
        Arrays.fill(values, null);
    }

    @Override
    public Set<K> keySet() {
        return toSet(keys);
    }

    @Override
    public Collection<V> values() {
        return toSet(values);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        HashSet<Entry<K, V>> set = new HashSet<>();
        for (K key : keys) {
            set.add(Map.entry(key, get(key)));
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static <T> Set<T> toSet(T[] array) {
        HashSet<T> set = new HashSet<>(Arrays.asList(array));
        set.remove(null);
        return set;
    }

}
