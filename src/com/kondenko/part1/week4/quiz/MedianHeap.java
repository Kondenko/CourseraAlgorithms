package com.kondenko.part1.week4.quiz;


import static com.kondenko.CompareUtils.gt;
import static com.kondenko.CompareUtils.min;

public class MedianHeap<T extends Comparable<T>> {

    private PriorityQueue<T> minHeap = PriorityQueue.min();

    private PriorityQueue<T> maxHeap = PriorityQueue.max();

    public void insert(T item) {
        T median = findMedian();
        if (median == null || gt(item, median)) minHeap.add(item);
        else maxHeap.add(item);
        restoreBalance();
    }

    public T findMedian() {
        return getMedian(false);
    }

    public T removeMedian() {
        return getMedian(true);
    }

    private T getMedian(boolean remove) {
        if (minHeap.size() > maxHeap.size()) {
            return remove ? minHeap.removeRoot() : minHeap.root();
        } else if (minHeap.size() < maxHeap.size()) {
            return remove ? maxHeap.removeRoot() : maxHeap.root();
        }
        T minRoot = remove ? minHeap.removeRoot() : minHeap.root();
        T maxRoot = remove ? maxHeap.removeRoot() : maxHeap.root();
        if (minRoot != null && maxRoot != null) return min(minRoot, maxRoot);
        if (minRoot != null) return minRoot;
        return maxRoot;
    }

    private void restoreBalance() {
        if (minHeap.size() < maxHeap.size() - 1) minHeap.add(maxHeap.removeRoot());
        else if (maxHeap.size() < minHeap.size() - 1) maxHeap.add(minHeap.removeRoot());
    }

}
