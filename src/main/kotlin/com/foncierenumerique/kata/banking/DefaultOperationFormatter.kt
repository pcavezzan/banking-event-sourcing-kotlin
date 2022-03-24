package com.foncierenumerique.kata.banking

import java.time.format.DateTimeFormatter


private const val SEPARATOR = "   "

class DefaultOperationFormatter {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.M.yyyy")

    fun format(operations: List<Operation>): String =
        "Date${SEPARATOR}Amount${SEPARATOR}Balance${System.lineSeparator()}" +
                operations.joinToString(separator = System.lineSeparator()) {
                    "${it.date.format(dateTimeFormatter)}${SEPARATOR}${format(it.type)}${it.amount}${SEPARATOR}${it.balance}"
                }

    private fun format(type: OperationType): String = when (type) {
        OperationType.DEPOSIT -> "+"
        OperationType.WITHDRAW -> "-"
    }


}
