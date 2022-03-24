package com.foncierenumerique.kata.banking

import java.time.LocalDate

sealed class Command(val amount: Int, val date: LocalDate)

class DepositAmountCommand(amount: Int, date: LocalDate) : Command(amount, date)
class WithdrawAmountCommand(amount: Int, date: LocalDate) : Command(amount, date)

class CommandFactory(private val time: Time) {
    fun newDeposit(amount: Int) = DepositAmountCommand(amount, time.now())
    fun newWithDraw(amount: Int) = WithdrawAmountCommand(amount, time.now())
}