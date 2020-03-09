package com.kondenko.part1.week5.quiz;

import com.kondenko.Utils;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class DocumentSearchTest {

    @Test
    public void shouldFind() {
        String[] document = "quick brown fox jumps over the lazy dog".split(" ");
        String[] query = "lazy dog".split(" ");
        assertEquals(2, DocumentSearch.search(document, query));
    }

    @Test
    public void shouldFindShortestInterval() {
        String[] document = "lazy and happy dog and quick brown fox jumps over the lazy dog".split(" ");
        String[] query = "lazy dog".split(" ");
        assertEquals(2, DocumentSearch.search(document, query));
    }

    @Test
    public void shouldNotFind() {
        String[] document = "quick brown fox jumps over the lazy dog".split(" ");
        String[] query = "fat cat".split(" ");
        assertEquals(-1, DocumentSearch.search(document, query));
    }

    @Test
    public void shouldNotFindInReverseOrder() {
        String[] document = "quick brown fox jumps over the lazy dog".split(" ");
        String[] query = "dog lazy".split(" ");
        assertEquals(-1, DocumentSearch.search(document, query));
    }

    @Test
    public void shouldFindInLargeString() {
        int size = 1_000_000;
        String[] document = new String[size];
        for (int i = 0; i < size; i++) {
            document[i] = String.valueOf(i);
        }
        String[] query = Arrays.copyOfRange(document, size / 2, size);
        AtomicInteger interval = new AtomicInteger();
        long time = Utils.measureTime(() -> {
            interval.set(DocumentSearch.search(document, query));
        });
        StdOut.printf("Document length: %d words, query length: %d words, processed in %d ms\n", size, query.length, time);
        assertEquals(query.length, interval.get());
    }

}