package com.kondenko.part1.week1.quiz


import com.kondenko.algs4.WeightedQuickUnionUF
import kotlin.random.Random

fun main() {
    val n = 100_000
    val socialUF = SocialUF(n)
    val connections = getConnections(n)
    val timestamp = getTimestamp(socialUF, connections)
    print("Found timestamp $timestamp for ${socialUF.getComplexity()} ops")
}

private fun getTimestamp(socialUF: SocialUF, connections: Array<Connection>): Int {
    for (c in connections) {
        if (socialUF.unionAndMatch(c.personA, c.personB)) return c.timestamp
    }
    return 0
}

private fun getConnections(n: Int): Array<Connection> {
    val original = (0 until n).toList()
    val shuffled = original.shuffled()
    return original.zip(shuffled) { a, b -> Connection(Random.nextInt(), a, b) }
            .sortedBy { it.timestamp }
            .also { println(it) }
            .toTypedArray()
}

internal data class Connection(var timestamp: Int, var personA: Int, var personB: Int)

internal class SocialUF(n: Int) : WeightedQuickUnionUF(n) {

    var complexity = 0

    fun getComplexity() = "$complexity log ${parent.size}"

    fun unionAndMatch(p: Int, q: Int): Boolean {
        complexity++
        val rootP = find(p)
        val rootQ = find(q)
        if (rootP == rootQ) return true
        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ
            size[rootQ] += size[rootP]
        } else {
            parent[rootQ] = rootP
            size[rootP] += size[rootQ]
        }
        count--
        return false
    }

}
