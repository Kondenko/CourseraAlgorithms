package com.kondenko.week6.quiz;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeparateChainingHashTable<K, V> implements Map<K, V> {

    private static class Node {

        private Object key;
        private Object value;
        private Node next;

        private Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    protected int m;

    private Node[] nodes;

    private int size = 0;

    private int numberOfFilledBuckets = 0;

    public SeparateChainingHashTable() {
        this.m = 10;
        nodes = new Node[m];
    }

    public SeparateChainingHashTable(int m) {
        this.m = m;
        nodes = new Node[m];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    public final Stream<Node> nodes() {
        Stream.Builder<Node> builder = Stream.builder();
        for (Node node : nodes) {
            for (Node j = node; j != null; j = j.next) {
                builder.accept(j);
            }
        }
        return builder.build();
    }

    private void grow() {
        if (numberOfFilledBuckets >= m - 1) {
            m *= 2;
            nodes = Arrays.copyOf(nodes, m);
        }
    }

    private void shrink() {
        if (numberOfFilledBuckets < m / 4) {
            m /= 2;
            nodes = Arrays.copyOf(nodes, m);
        }
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        Node nodeAtHash = nodes[hash];
        nodes[hash] = new Node(key, value, nodeAtHash);
        if (nodes[hash].next != null) {
            if (value != null) numberOfFilledBuckets++;
            else numberOfFilledBuckets--;
        }
        if (value != null) {
            size++;
            grow();
        } else {
            size--;
            shrink();
        }
        return value;
    }

    @Override
    public V get(Object key) {
        Node root = nodes[hash((K) key)];
        for (Node i = root; i != null; i = i.next) {
            if (i.key.equals((K) key)) return (V) i.value;
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
        return nodes().anyMatch(n -> n.value.equals(value));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        Arrays.fill(nodes, null);
    }

    @Override
    public Set<K> keySet() {
        return nodes().map(n -> (K) n.key).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return nodes().map(n -> (V) n.value).collect(Collectors.toSet());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return nodes().map(n -> Map.entry((K) n.key, (V) n.value)).collect(Collectors.toSet());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

}
