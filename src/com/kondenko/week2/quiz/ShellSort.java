package com.kondenko.week2.quiz;

import com.kondenko.ArrayUtils;
import com.kondenko.CompareUtils;


public class ShellSort {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int step = 1;
        while (step < arr.length / 3) {
            step = 3 * step + 1;
        }
        while (step >= 1) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i; j >= step && CompareUtils.lt(arr[i], arr[j - step]); j -= step) {
                    ArrayUtils.swap(arr, j, j - step);
                    i--;
                }
            }
            step /= 3;
        }
    }

    public static void sort(int[] arr) {
        int step = 1;
        while (step < arr.length / 3) {
            step = 3 * step + 1;
        }
        while (step >= 1) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i; j >= step && arr[i] < arr[j - step]; j -= step) {
                    ArrayUtils.swap(arr, j, j - step);
                    i--;
                }
            }
            step /= 3;
        }
    }


}
