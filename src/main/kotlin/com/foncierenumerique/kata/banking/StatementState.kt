package com.foncierenumerique.kata.banking

class StatementState(statementFormatter: DefaultOperationFormatter) {

    private val statement = Statement(formatter = statementFormatter)

    fun apply(events: List<Event>): Statement {
        val operations = events.map {
            when (it) {
                is AmountWithdrawnEvent -> applyAmountWithDrawnEvent(it)
                is AmountDepositedEvent -> applyAmountDepositedEvent(it)
            }
        }
        return statement.apply(operations)
    }

    private fun applyAmountDepositedEvent(event: AmountDepositedEvent): Operation {
        return Operation(
            type = OperationType.DEPOSIT,
            amount = event.amount,
            date = event.date
        )
    }

    private fun applyAmountWithDrawnEvent(event: AmountWithdrawnEvent): Operation {
        return Operation(
            type = OperationType.WITHDRAW,
            amount = event.amount,
            date = event.date
        )
    }
}
