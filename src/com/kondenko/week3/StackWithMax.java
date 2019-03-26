package com.kondenko.week3;

import edu.princeton.cs.algs4.Stack;

import static com.kondenko.Utils.println;

public class StackWithMax {

    private int max = 0;

    private Stack<Integer> stack = new Stack<>();

    public void push(int item) {
        if (stack.isEmpty() || item > max) {
            max = item;
        }
        stack.push(item);
    }

    public int pop() {
        int item = stack.pop();
        if (item == max) {
            recalculateMax();
        }
        return item;
    }

    public int max() {
        return max;
    }

    public int size() {
        return stack.size();
    }

    private void recalculateMax() {
        println("recalculateMax called on %d elements", stack.size());
        max = stack.peek();
        for (Integer integer : stack) {
            if (integer > max) {
                max = integer;
            }
        }
    }

}
