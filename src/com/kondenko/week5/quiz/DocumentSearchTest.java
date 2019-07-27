package com.kondenko.week5.quiz;

import org.junit.Test;

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
        String[] document = "lazy and happy dog quick brown fox jumps over the lazy dog".split(" ");
        String[] query = "lazy dog".split(" ");
        assertEquals(2, DocumentSearch.search(document, query));
    }

    @Test
    public void shouldNotFind() {
        String[] document = "quick brown fox jumps over the lazy dog".split(" ");
        String[] query = "fat cat".split(" ");
        assertEquals(-1, DocumentSearch.search(document, query));
    }

}