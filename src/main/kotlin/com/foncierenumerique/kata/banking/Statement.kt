package com.foncierenumerique.kata.banking

import java.time.format.DateTimeFormatter

private const val SEPARATOR = "   "

data class Statement(
    private val operations: List<Operation> = mutableListOf()
) {

    fun print(): String =
        "Date${SEPARATOR}Amount${SEPARATOR}Balance${System.lineSeparator()}" +
                operations.joinToString(separator = System.lineSeparator()) {
                    "${it.date.format(DateTimeFormatter.ISO_DATE)}${SEPARATOR}${format(it.type)}${it.amount}${SEPARATOR}${it.balance}"
                }

    private fun format(type: OperationType): String = when (type) {
        OperationType.DEPOSIT -> "+"
        OperationType.WITHDRAW -> "-"
    }


    companion object {
        fun apply(events: List<Event>): Statement {
            var balance = 0
            return Statement(
                events.map {
                    when (it) {
                        is AmountWithdrawnEvent -> {
                            balance -= it.amount
                            Operation(
                                type = OperationType.WITHDRAW,
                                amount = it.amount,
                                date = it.date,
                                balance = balance
                            )
                        }
                        is AmountDepositedEvent -> {
                            balance += it.amount
                            Operation(
                                type = OperationType.DEPOSIT,
                                amount = it.amount,
                                date = it.date,
                                balance = balance
                            )
                        }
                    }
                })
        }
    }

}
