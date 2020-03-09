package com.kondenko.part1.week2.quiz;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class DutchNationalFlagTest {

    @Test
    public void sort() {
        Color[] input = {
                Color.WHITE,
                Color.RED,
                Color.WHITE,
                Color.BLUE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.BLUE,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED
        };
        Color[] expected = {
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.RED,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE,
                Color.BLUE
        };
        DutchNationalFlag dnf = new DutchNationalFlag(input);
        Color[] actual = dnf.sort();
        assertArrayEquals(expected, actual);
    }
}