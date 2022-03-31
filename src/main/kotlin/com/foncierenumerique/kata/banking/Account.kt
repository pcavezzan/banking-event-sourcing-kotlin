package com.foncierenumerique.kata.banking

class Account(
    private val commandFactory: CommandFactory,
    private val amountDecision: AmountCommandDecision,
    private val eventStore: EventStore,
    private val state: StatementState,
) {
    var statement = state.apply(emptyList())

    fun deposit(amount: Int) {
        val events = amountDecision.decide(statement.balance, commandFactory.newDeposit(amount))
        eventStore.saveAll(events)
        statement = state.apply(eventStore.findAll())
    }

    fun withdraw(amount: Int) {
        val events = amountDecision.decide(statement.balance, commandFactory.newWithDraw(amount))
        eventStore.saveAll(events)
        statement = state.apply(eventStore.findAll())
    }

    fun printStatement(): String = statement.print()

}
