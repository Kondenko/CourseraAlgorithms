package com.kondenko.week6.quiz;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SeparateChainingHashTableTest {

    SeparateChainingHashTable<Integer, String> table;

    @Before
    public void setUp() {
        table = new SeparateChainingHashTable<>();
    }

    @Test
    public void shouldReturnValue() {
        Integer key = 0;
        String value = "a";
        table.put(key, value);
        assertFalse(table.isEmpty());
        assertEquals(value, table.get(key));
        assertTrue(table.containsKey(key));
        assertTrue(table.containsValue(value));
    }

    @Test
    public void shouldReturnNull() {
        Integer key = 0;
        String value = "a";
        assertNull(table.get(1));
        assertFalse(table.containsKey(key));
        assertFalse(table.containsValue(value));
        assertTrue(table.isEmpty());
    }

    @Test
    public void shouldStoreKeysWithSameHash() {
        SeparateChainingHashTable<Foo, String> table = new SeparateChainingHashTable<>();
        Foo key1 = new Foo(0);
        String value1 = "a";
        Foo key2 = new Foo(1);
        String value2 = "b";
        table.put(key1, value1);
        table.put(key2, value2);
        assertEquals(2, table.size());
        assertEquals(value1, table.get(key1));
        assertEquals(value2, table.get(key2));
        assertEquals(Set.of(key1, key2), table.keySet());
    }

    @Test
    public void shouldFillAllBucketsAndGrow() {
        int initialM = 1;
        SeparateChainingHashTable<Object, Integer> table = new SeparateChainingHashTable<>(initialM);
        Object key = new Object() {
            @Override
            public int hashCode() {
                return 0;
            }
        };
        assertEquals(initialM, table.m);
        table.put(key, 0);
        assertEquals(initialM * 2, table.m);
        table.put(new Object() {
            @Override
            public int hashCode() {
                return 1;
            }
        }, 1);
        assertEquals(initialM * 2, table.m);
        table.remove(key);
        assertEquals(initialM, table.m);
    }

    private static class Foo {

        private int value;

        public Foo(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Foo foo = (Foo) o;
            return value == foo.value;
        }

        @Override
        public int hashCode() {
            return 17;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "value=" + value +
                    '}';
        }

    }

}