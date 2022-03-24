package com.foncierenumerique.kata.banking

import java.time.LocalDate
import java.util.*

interface Time {
    fun now(): LocalDate
}

class FakeTime(private val dates: Stack<LocalDate>) : Time {
    override fun now(): LocalDate {
        if (dates.isEmpty()) error("No more dates available")
        return dates.pop()
    }
}
