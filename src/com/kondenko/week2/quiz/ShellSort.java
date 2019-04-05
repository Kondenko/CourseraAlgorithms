package com.kondenko.week2.quiz;

public class ShellSort {

    public static <T extends Comparable<T>> void shellSort(T[] arr) {
        int step = 1;
        while (step < arr.length / 3) {
            step = 3 * step + 1;
        }
        while (step >= 1) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i; j >= step && less(arr[i], arr[j - step]); j -= step) {
                    swap(arr, j, j - step);
                    i--;
                }
            }
            step /= 3;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

}
