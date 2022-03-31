package com.foncierenumerique.kata.banking

import org.slf4j.LoggerFactory

private val LOGGER = LoggerFactory.getLogger(AmountCommandDecision::class.java)

class AmountCommandDecision {

    fun decide(balance: Int, command: Command): List<Event> = when (command) {
        is DepositAmountCommand -> handleDepositCommand(command)
        is WithdrawAmountCommand -> handleWithdrawCommand(balance, command)
    }

    private fun handleWithdrawCommand(balance: Int, command: WithdrawAmountCommand): List<Event> =
        if (balance < command.amount) {
            LOGGER.info("Cannot withdraw more money ({}) than i have ({})", command.amount, balance)
            emptyList()
        } else {
            listOf(AmountWithdrawnEvent(command.amount, command.date))
        }

    private fun handleDepositCommand(command: DepositAmountCommand): List<Event> =
        listOf(AmountDepositedEvent(command.amount, command.date))

}
