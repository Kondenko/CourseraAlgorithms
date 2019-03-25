package com.kondenko.week3;

import edu.princeton.cs.algs4.Stack;

public class TwoStackQueue<T> {

    private Stack<T> in = new Stack<>();

    private Stack<T> out = new Stack<>();

    public void enqueue(T item) {
        in.push(item);
    }

    public T dequeue() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    public int size() {
        return in.size() + out.size();
    }

}