package com.foncierenumerique.kata.banking

import java.time.LocalDate

data class Operation(
    val type: OperationType,
    val date: LocalDate,
    val amount: Int
)

enum class OperationType {
    WITHDRAW,
    DEPOSIT;
}
