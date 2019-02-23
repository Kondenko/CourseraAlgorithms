package com.kondenko.week1.quiz

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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