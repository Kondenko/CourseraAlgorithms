package com.kondenko.week2.quiz;

public class BinarySearch {

    public static <T extends Comparable<T>> int search(T item, T[] sortedArr) {
        int left = 0;
        int mid = sortedArr.length / 2;
        int right = sortedArr.length - 1;
        while (left != mid && right != mid) {
            int comparison = item.compareTo(sortedArr[mid]);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid;
            } else {
                left = mid;
            }
            mid = (right + left) / 2;
        }
        return -1;
    }


}
