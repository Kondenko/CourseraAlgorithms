package com.kondenko.week3.quiz;

import static com.kondenko.ArrayUtils.isSorted;
import static com.kondenko.ArrayUtils.swap;
import static com.kondenko.CompareUtils.gt;

public class MergingWithSmallerArray {

    public static <T extends Comparable<T>> void merge(T[] arr) {
        final int n = arr.length / 2;
        assert isSorted(arr, 0, n - 1);
        assert isSorted(arr, n, 2 * n - 1);
        T[] aux = (T[]) new Comparable[n];
        int i = 0;
        int j = n;
        for (int k = 0, pass = 0; pass < 2; k++) {
            if (k == aux.length) {
                if (pass == 0) {
                    int[] ij = shrink(arr, i, j);
                    i = ij[0];
                    j = ij[1];
                }
                append(aux, arr, pass);
                k = -1;
                pass++;
            } else {
                if (arr[i] == null) {
                    aux[k] = arr[j];
                    arr[j++] = null;
                } else if (j >= arr.length) {
                    aux[k] = arr[i];
                    arr[i++] = null;
                } else if (gt(arr, i, j)) {
                    aux[k] = arr[j];
                    arr[j++] = null;
                } else {
                    aux[k] = arr[i];
                    arr[i++] = null;
                }
            }
        }
    }

    protected static <T extends Comparable<T>> void append(T[] aux, T[] arr, int padding) {
        for (int i = 0; i < aux.length; i++) {
            arr[i + aux.length * padding] = aux[i];
            aux[i] = null;
        }
    }

    protected static <T extends Comparable<T>> int[] shrink(T[] arr, int i, int j) {
        for (int k = arr.length - 1; k >= 0; k--) {
            if (arr[k] == null) {
                for (int m = k - 1; m >= 0; m--) {
                    if (arr[m] != null) {
                        swap(arr, k, m);
                        if (k == i || m == i) i += k - m;
                        if (k == j || m == j) j += k - m;
                        break;
                    }
                }
            }
        }
        return new int[]{i, j};
    }

}
