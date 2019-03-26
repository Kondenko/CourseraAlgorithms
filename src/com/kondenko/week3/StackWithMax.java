package com.kondenko.week3;

import edu.princeton.cs.algs4.Stack;

public class StackWithMax {

    private Stack<Integer> stack = new Stack<>();

    private Stack<Integer> maxStack = new Stack<>();

    public void push(int item) {
        if (maxStack.isEmpty() || item > maxStack.peek()) {
            maxStack.push(item);
        }
        stack.push(item);
    }

    public int pop() {
        int item = stack.pop();
        if (item == maxStack.peek()) {
            maxStack.pop();
        }
        return item;
    }

    public int max() {
        return maxStack.peek();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
