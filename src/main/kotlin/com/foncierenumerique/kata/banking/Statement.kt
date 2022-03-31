package com.foncierenumerique.kata.banking

data class Statement(
    private val operations: List<Operation> = mutableListOf(),
    private val formatter: DefaultOperationFormatter
) {
    val balance: Int =
        operations.filter { it.type == OperationType.DEPOSIT }
            .sumOf { it.amount } - operations.filter { it.type == OperationType.WITHDRAW }.sumOf { it.amount }


    fun print(): String = formatter.format(operations)
    fun apply(operations: List<Operation>): Statement = copy(operations = operations)
}
