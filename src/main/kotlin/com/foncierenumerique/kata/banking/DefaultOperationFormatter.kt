package com.foncierenumerique.kata.banking

import java.time.format.DateTimeFormatter


private const val SEPARATOR = "   "

class DefaultOperationFormatter {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.M.yyyy")

    fun format(operations: List<Operation>): String {
        var balance = 0
        val statementLines = operations.map {
            balance = balance.withOperation(it)
            StatementLine(
                it.date.format(dateTimeFormatter),
                "${format(it.type)}${it.amount}",
                "$balance"
            )
        }

        return "Date${SEPARATOR}Amount${SEPARATOR}Balance${System.lineSeparator()}" +
                statementLines.joinToString(separator = System.lineSeparator()) {
                    "${it.date}${SEPARATOR}${it.amount}${SEPARATOR}${it.balance}"
                }
    }


    private fun format(type: OperationType): String = when (type) {
        OperationType.DEPOSIT -> "+"
        OperationType.WITHDRAW -> "-"
    }

    data class StatementLine(
        val date: String,
        val amount: String,
        val balance: String
    )

}

private fun Int.withOperation(operation: Operation): Int = if (operation.type == OperationType.DEPOSIT) {
    this + operation.amount
} else
    this - operation.amount
