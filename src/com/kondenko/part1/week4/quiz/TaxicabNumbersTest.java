package com.kondenko.part1.week4.quiz;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class TaxicabNumbersTest {

    @Test
    public void findTaxicabNumbers() {
        Integer[] expected = new Integer[]{1729};
        Integer[] actual = TaxicabNumbers.find(14);
        assertThat(Arrays.asList(actual), containsInAnyOrder(expected));
    }

}