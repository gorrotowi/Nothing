package com.gorro.nothing

typealias MonthDay = Pair<Int, Int>

/**
 * Extension function that validates it is December 24th or December 25th
 *
 * It checks the month and the day on a [MonthDay] (which is actually just a [Pair] of [Int]
 *
 * @return true if December 24th or December 25th
 */
fun MonthDay.isItChristmas(): Boolean = first == 11 && (second == 24 || second == 25)
