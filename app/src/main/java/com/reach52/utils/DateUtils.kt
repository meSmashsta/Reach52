package com.reach52.utils

import java.util.*

fun stringFromLongDate(longDate: Long): String {

    val date = Date(longDate)
    val y = date.year
    val m = date.month + 1
    val d = date.date
    return "$d/$m/$y"

}

fun calculateAge(dobLong: Long): Int {

    val dateDoB = Date(dobLong)

    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()

    dob.set(dateDoB.year, dateDoB.month, dateDoB.date)

    var age = today[Calendar.YEAR] - dob[Calendar.YEAR]

    if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
        age--
    }

    return age
}