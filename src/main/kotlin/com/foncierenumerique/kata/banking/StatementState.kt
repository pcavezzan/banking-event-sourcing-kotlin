package com.foncierenumerique.kata.banking

class StatementState(private val statementFormatter: DefaultOperationFormatter) {

    var balance = 0

    fun apply(events: List<Event>): Statement {
        val operations = events.map {
            when (it) {
                is AmountWithdrawnEvent -> applyAmountWithDrawnEvent(it)
                is AmountDepositedEvent -> applyAmountDepositedEvent(it)
            }
        }
        return Statement(operations, statementFormatter)
    }

    private fun applyAmountDepositedEvent(event: AmountDepositedEvent): Operation {
        balance += event.amount
        return Operation(
            type = OperationType.DEPOSIT,
            amount = event.amount,
            date = event.date,
            balance = balance
        )
    }

    private fun applyAmountWithDrawnEvent(event: AmountWithdrawnEvent): Operation {
        var balance1 = balance
        balance1 -= event.amount
        return Operation(
            type = OperationType.WITHDRAW,
            amount = event.amount,
            date = event.date,
            balance = balance1
        )
    }

}
