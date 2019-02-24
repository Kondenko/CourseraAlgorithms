package com.kondenko.week1.quiz

import com.kondenko.algs4.WeightedQuickUnionUF
import junit.framework.Assert.assertEquals
import org.junit.Test
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

internal class WeightedQuickUnionUFWithMaxItemTest {

    @Test
    fun `should find max`() {
        val uf = WeightedQuickUnionUFWithMaxItem(10)
        uf.run {
            union(1, 2)
            union(2, 3)
            union(1, 5)
        }
        assertEquals(5, uf.find(3))
    }

    @Test
    fun `should find nothing`() {
        val uf = WeightedQuickUnionUFWithMaxItem(10)
        assertEquals(0, uf.find(3))
    }

}