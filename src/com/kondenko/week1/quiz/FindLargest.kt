package com.kondenko.week1.quiz

import edu.princeton.cs.algs4.WeightedQuickUnionUF
import kotlin.math.max

class WeightedQuickUnionUFWithMaxItem(n: Int) : WeightedQuickUnionUF(n) {

    private val max = IntArray(n) { 0 }

    override fun find(p: Int): Int {
        return max[root(p)]
    }

    fun root(p: Int) = super.find(p)

    override fun union(p: Int, q: Int) {
        val rootP = root(p)
        val rootQ = root(q)
        if (rootP == rootQ) return
        val maxItem = max(p, q)
        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ
            max[rootQ] = maxItem
            size[rootQ] += size[rootP]
        } else {
            parent[rootQ] = rootP
            max[rootP] = maxItem
            size[rootP] += size[rootQ]
        }
        count--
    }

}