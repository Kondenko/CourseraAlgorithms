package com.kondenko.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumQuadratic {

    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> results = new ArrayList<>();
        sort(nums);
        int size = nums.length;
        // [-4, -1, -1, 0, 1, 2]
        for (int i = 0; i < size; i++) {
            int minusC = -nums[i];
            int left = i + 1;
            int right = size - 1;
            while (left < right) {
                int a = nums[left];
                int b = nums[right];
                if (a + b == minusC) {
                    left++;
                    right--;
                    List<Integer> result = Arrays.asList(-minusC, a, b);
                    if (!results.contains(result)) results.add(result);
                } else if (a + b > minusC) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return results;
    }

    private static int[] sort(int[] arr) {
        if (arr.length == 1) return arr;
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > num) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = num;
        }
        return arr;
    }

}
