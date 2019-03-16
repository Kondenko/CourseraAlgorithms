package com.kondenko.week2;

import org.junit.Test;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

public class EggDropTest {

    private int eggs = 2;

    @Test
    public void findT() {
        int n = 25;
        int t = 25;
        EggDrop ed = new EggDrop(t, eggs, getTosses(n));
        assertEquals(t, ed.findT(n));
    }

    @Test
    public void findT2() {
        int n = 25;
        int t = 1;
        EggDrop ed = new EggDrop(t, eggs, getTosses(n));
        assertEquals(t, ed.findT(n));
    }

    @Test
    public void findT3() {
        int n = 25;
        int t = 11;
        EggDrop ed = new EggDrop(t, eggs, getTosses(n));
        assertEquals(t, ed.findT(n));
    }

    @Test
    public void findT4() {
        int n = 7;
        int t = 5;
        EggDrop ed = new EggDrop(t, eggs, getTosses(n));
        assertEquals(t, ed.findT(n));
    }

    private int getTosses(int n) {
        return (int) round(2*sqrt(n));
    }


}