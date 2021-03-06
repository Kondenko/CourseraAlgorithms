package com.kondenko.part1.week2.quiz;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(T item, T[] sortedArr) {
        int left = 0;
        int mid = sortedArr.length / 2;
        int right = sortedArr.length - 1;
        while (left <= right) {
            int comparison = item.compareTo(sortedArr[mid]);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (right + left) / 2;
        }
        return -1;
    }

    public static int search(int item, int[] sortedArr) {
        int left = 0;
        int mid = sortedArr.length / 2;
        int right = sortedArr.length - 1;
        while (left <= right) {
            if (item == sortedArr[mid]) {
                return mid;
            } else if (item < sortedArr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (right + left) / 2;
        }
        return -1;
    }


}
