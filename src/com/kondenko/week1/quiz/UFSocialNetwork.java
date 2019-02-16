package com.kondenko.week1.quiz;


import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class UFSocialNetwork {

    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        int n = 10;
        SocialUF socialUF = new SocialUF(n);
        Connection[] connections = getConnections(n);
        print(getTimestamp(socialUF, connections) + " ");
    }

    private static int getTimestamp(SocialUF socialUF, Connection[] connections) {
        for (Connection c : connections) {
            if (socialUF.unionAndMatch(c.personA, c.personB)) return c.timestamp;
        }
        return 0;
    }

    private static Connection[] getConnections(int n) {
        int timestamp = n * 10;
        ArrayList<Connection> connections = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                connections.add(new Connection(timestamp, i, 0));
            } else {
                connections.add(new Connection(timestamp, i, i + 1));
            }
        }
        Collections.shuffle(connections);
        for (int i = 0; i < n; i++) {
            timestamp++;
            connections.get(i).timestamp = timestamp;
        }
        print("Connections: \n" + connections + "\n max timestamp = " + timestamp);
        return connections.toArray(Connection[]::new);
    }

    static class Connection {

        int timestamp;
        int personA;
        int personB;

        public Connection(int timestamp, int personA, int personB) {
            this.timestamp = timestamp;
            this.personA = personA;
            this.personB = personB;
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "timestamp=" + timestamp +
                    ", personA=" + personA +
                    ", personB=" + personB +
                    '}';
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
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
            return false;
        }
    }

}
