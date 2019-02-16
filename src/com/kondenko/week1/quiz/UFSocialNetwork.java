package com.kondenko.week1.quiz;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class UFSocialNetwork {

    public static void main(String[] args) {
        int n = 10;
        SocialUF socialUF = new SocialUF(n);
        Connection[] connections = getConnections(n);
    }

    private static Connection[] getConnections(int n) {
        Connection[] connections = new Connection[n];
        // TODO Generate connections
        return connections;
    }

    class Connection {

        int timestamp;
        int personA;
        int personB;

        public Connection(int timestamp, int personA, int personB) {
            this.timestamp = timestamp;
            this.personA = personA;
            this.personB = personB;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public int getPersonA() {
            return personA;
        }

        public int getPersonB() {
            return personB;
        }

    }

    static class SocialUF extends WeightedQuickUnionUF {

        /**
         * Initializes an empty union–find data structure with {@code n} sites
         * {@code 0} through {@code n-1}. Each site is initially in its own
         * component.
         *
         * @param n the number of sites
         * @throws IllegalArgumentException if {@code n < 0}
         */
        public SocialUF(int n) {
            super(n);
        }

        public boolean unionAndMatch(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return true;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
            return false;
        }
    }

}
