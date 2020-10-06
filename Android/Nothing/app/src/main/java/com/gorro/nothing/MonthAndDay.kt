package com.gorro.nothing

typealias MonthDay = Pair<Int, Int>

fun MonthDay.isItChristmas(): Boolean = first == 11 && (second == 24 || second == 25)