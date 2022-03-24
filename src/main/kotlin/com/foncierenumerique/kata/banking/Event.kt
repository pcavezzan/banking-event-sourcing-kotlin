package com.foncierenumerique.kata.banking

import java.time.LocalDate

sealed class Event(val amount: Int, val date: LocalDate)

class AmountDepositedEvent(amount: Int, date: LocalDate) : Event(amount, date)
class AmountWithdrawnEvent(amount: Int, date: LocalDate) : Event(amount, date)
