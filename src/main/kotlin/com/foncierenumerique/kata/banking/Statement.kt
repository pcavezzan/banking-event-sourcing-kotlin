package com.foncierenumerique.kata.banking

data class Statement(
    private val operations: List<Operation> = mutableListOf(),
    private val formatter: DefaultOperationFormatter
) {

    fun print(): String = formatter.format(operations)



}
