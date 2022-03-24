package com.foncierenumerique.kata.banking

class AmountCommandDecision {

    fun decide(command: Command): List<Event> = when (command) {
        is DepositAmountCommand -> handleDepositCommand(command)
        is WithdrawAmountCommand -> handleWithdrawCommand(command)
    }

    private fun handleWithdrawCommand(command: WithdrawAmountCommand): List<Event> =
        listOf(AmountWithdrawnEvent(command.amount, command.date))

    private fun handleDepositCommand(command: DepositAmountCommand): List<Event> =
        listOf(AmountDepositedEvent(command.amount, command.date))

}
