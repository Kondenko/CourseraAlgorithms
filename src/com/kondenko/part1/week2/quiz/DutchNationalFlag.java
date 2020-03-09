package com.kondenko.part1.week2.quiz;

import com.kondenko.ArrayUtils;

public class DutchNationalFlag {

    private Color[] buckets;

    private int swapCalls = 0;

    private int colorCalls = 0;

    public DutchNationalFlag(Color[] buckets) {
        this.buckets = buckets;
    }

    public Color[] sort() {
        int left = 0;
        int right = buckets.length - 1;
        int mid = 0;
        for (int i = 0; i < buckets.length; i++) {
            Color color = color(mid);
            if (color == Color.BLUE) {
                swap(mid, right--);
            } else if (color == Color.RED) {
                swap(mid++, left++);
            } else {
                mid++;
            }
        }
        return buckets;
    }

    private void swap(int i, int j) {
        assert swapCalls < buckets.length : String.format("Swap calls reached limit at %d for %d, %d (%d items)", swapCalls, i, j, buckets.length);
        swapCalls++;
        ArrayUtils.swap(buckets, i, j);
    }

    private Color color(int i) {
        assert colorCalls < buckets.length : String.format("Color calls reached limit at %d (%d/%d)", i, colorCalls, buckets.length);
        colorCalls++;
        return buckets[i];
    }

}
