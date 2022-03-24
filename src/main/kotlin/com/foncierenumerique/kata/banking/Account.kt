package com.foncierenumerique.kata.banking

class Account(
    private val commandFactory: CommandFactory,
    private val amountDecision: AmountCommandDecision,
    private val eventStore: EventStore
) {

    fun deposit(amount: Int) {
        val events = amountDecision.decide(commandFactory.newDeposit(amount))
        eventStore.saveAll(events)
    }

    fun withdraw(amount: Int) {
        val events = amountDecision.decide(commandFactory.newWithDraw(amount))
        eventStore.saveAll(events)
    }

    fun printStatement(): String {
        val events = eventStore.findAll()
        return Statement.apply(events).print()
    }

}
