package com.kondenko;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class Utils {

    public static int size(Iterable<?> iterable) {
        int size = 0;
        for (Object o : iterable) {
            size++;
        }
        return size;
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<>();
        iterable.iterator().forEachRemaining(list::add);
        return list;
    }

    public static <T> void forEachIndexed(Iterable<T> iterable, BiConsumer<T, Integer> action) {
        AtomicInteger index = new AtomicInteger(0);
        iterable.forEach(t -> action.accept(t, index.getAndIncrement()));
    }

    public static long measureTime(Runnable algorithm) {
        long start = System.currentTimeMillis();
        algorithm.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static int randomIntArray(int[] into) {
        Random rnd = new Random();
        int item = rnd.nextInt();
        int max = item;
        for (int i = 0; i < into.length; i++) {
            into[i] = item;
            if (item > max) {
                max = item;
            }
            item = rnd.nextInt();
        }
        return max;
    }

    public static void println(String s, Object... args) {
        System.out.println(String.format(s, args));
    }

}
