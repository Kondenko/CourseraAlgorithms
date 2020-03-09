package com.kondenko.part1.week6.quiz;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class LinearProbingHashTableTest {

    LinearProbingHashTable<Integer, String> table;

    @Before
    public void setUp() {
        table = new LinearProbingHashTable<>();
    }

    @Test(timeout = 500)
    public void shouldReturnValue() {
        Integer key = 0;
        String value = "a";
        table.put(key, value);
        assertFalse(table.isEmpty());
        assertEquals(value, table.get(key));
        assertTrue(table.containsKey(key));
        assertTrue(table.containsValue(value));
    }

    @Test(timeout = 500)
    public void shouldReturnNull() {
        Integer key = 0;
        String value = "a";
        assertNull(table.get(key));
        assertFalse(table.containsKey(key));
        assertFalse(table.containsValue(value));
        assertTrue(table.isEmpty());
    }

    @Test(timeout = 500)
    public void shouldStoreKeysWithSameHash() {
        LinearProbingHashTable<Foo, String> table = new LinearProbingHashTable<>();
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

    @Test(timeout = 500)
    public void shouldFillAllBucketsAndGrow() {
        int initialM = 1;
        LinearProbingHashTable<Object, Integer> table = new LinearProbingHashTable<>(initialM);
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
        assertEquals(initialM * 4, table.m);
        table.remove(key);
        assertEquals(initialM * 4, table.m);
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