package com.kondenko.week1.quiz

import org.junit.jupiter.api.Test
import kotlin.math.min
import kotlin.test.assertEquals

class SuccessorWithDelete(val set: MutableSet<Int>) {

    private val sorted = set.sorted().toMutableList()

    fun remove(x: Int) {
        set.remove(x)
        sorted.remove(x)
    }

    fun successor(x: Int): Int {
        val currentIndex = sorted.binarySearch(x)
        return sorted[min(currentIndex + 1, sorted.lastIndex)]
    }

}

internal class SuccessorWithDeleteTest {

    @Test
    fun `should return successor`() {
        val set = SuccessorWithDelete(mutableSetOf(10, 20, 30, 40))
        assertEquals(20, set.successor(10))
    }

    @Test
    fun `should return the same item`() {
        val set = SuccessorWithDelete(mutableSetOf(10, 20, 30, 40))
        assertEquals(40, set.successor(40))
    }

}