package com.kondenko.week2;

import org.junit.Test;

import static com.kondenko.Utils.println;
import static java.lang.Math.*;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class EggDrop {

    private int T;

    private int eggs;

    private int tosses;

    public EggDrop(int t, int eggs, int tosses) {
        T = t;
        this.eggs = eggs;
        this.tosses = tosses;
    }

    int findT(int n) {
        int step = (int) Math.round(sqrt(n));
        for (int i = step; i <= n; i += step) {
            if (isEggBroken(i)) return findTinRange(i - step, i+1);
        }
        throw new RuntimeException("T couldn't be found");
    }

    private int findTinRange(int bottom, int top) {
        for (int i = bottom + 1; i <= top; i++) {
            if (isEggBroken(i)) return i;
        }
        return top;
    }

    private boolean isEggBroken(int floor) {
        tosses--;
        boolean isBroken = floor >= T;
        if (isBroken) eggs--;
        if (eggs < 0) throw new RuntimeException(format("eggs are %d", eggs));
        if (tosses < 0) throw new RuntimeException(format("tosses are %d", tosses));
        return isBroken;
    }

}

