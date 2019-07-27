package com.kondenko.week5.quiz;

public class DocumentSearch {

    public static int search(String[] document, String[] query) {
        RedBlackBst<String, Integer> queryBst = toBst(query);
        int intervalLength = -1;
        int currentIntervalLength = -1;
        int previousFoundWordIndex = -1;
        for (String word : document) {
            Integer wordIndexInQuery = queryBst.get(word);
            if (wordIndexInQuery != null) {
                if (wordIndexInQuery == 0) {
                    currentIntervalLength = 0;
                }
                if (wordIndexInQuery > previousFoundWordIndex) {
                    previousFoundWordIndex = wordIndexInQuery;
                    currentIntervalLength++;
                }
                if (wordIndexInQuery == query.length - 1 && (currentIntervalLength < intervalLength || intervalLength == -1)) {
                    intervalLength = currentIntervalLength;
                }
            } else {
                intervalLength = -1;
                currentIntervalLength = -1;
                previousFoundWordIndex = -1;
            }
        }
        return intervalLength;
    }

    private static RedBlackBst<String, Integer> toBst(String[] query) {
        RedBlackBst<String, Integer> bst = new RedBlackBst<>();
        for (int i = 0; i < query.length; i++) {
            bst.put(query[i], i);
        }
        return bst;
    }

}
