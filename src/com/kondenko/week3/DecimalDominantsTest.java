package com.kondenko.week3;

import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class DecimalDominantsTest {

    @Test
    public void find() {
        int keysNumber = 10;
        Integer[] array = {8, 9, 5, 3, 1, 2, 6, 10, 7, 4, 2, 2, 1};
        Integer[] expected = {1, 2};
        assertThat(DecimalDominants.find(array, keysNumber), containsInAnyOrder(expected));
    }

    @Test
    public void find2() {
        int keysNumber = 10;
        int itemsNumber = 100 + keysNumber;
        Integer[] array = new Integer[itemsNumber];
        for (int i = 0; i < itemsNumber; i++) {
            array[i] = i % keysNumber;
        }
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(DecimalDominants.find(array, keysNumber), containsInAnyOrder(expected));
    }

}