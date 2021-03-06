package com.gorro.nothing

import android.util.Log
import java.util.*

typealias MonthDay = Pair<Int, Int>

/**
 * Extension function that validates it is December 24th or December 25th
 *
 * It checks the month and the day on a [MonthDay] (which is actually just a [Pair] of [Int]
 *
 * @return true if December 24th or December 25th
 */
fun MonthDay.isItChristmas(): Boolean = first == 11 && (second == 24 || second == 25)


fun getCurrentMonthAndDay(): MonthDay {
    val calendar = Calendar.getInstance()
    Log.e("Calendar", calendar.toString())
    return MonthDay(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
}