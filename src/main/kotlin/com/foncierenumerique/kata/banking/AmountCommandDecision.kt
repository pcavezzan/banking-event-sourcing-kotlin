package com.foncierenumerique.kata.banking

import org.slf4j.LoggerFactory

private val LOGGER = LoggerFactory.getLogger(AmountCommandDecision::class.java)

class AmountCommandDecision {

    fun decide(balance: Balance, command: Command): List<Event> = when (command) {
        is DepositAmountCommand -> handleDepositCommand(command)
        is WithdrawAmountCommand -> handleWithdrawCommand(balance, command)
    }

    private fun handleWithdrawCommand(balance: Balance, command: WithdrawAmountCommand): List<Event> =
        if (balance.isNegative()) {
            LOGGER.info("Cannot withdraw any money ({}) when balance ({}) is negative", command.amount, balance.value)
            emptyList()
        } else if (balance.value < command.amount) {
            LOGGER.info("Cannot withdraw more money ({}) than i have ({})", command.amount, balance.value)
            emptyList()
        } else {
            listOf(AmountWithdrawnEvent(command.amount, command.date))
        }

    private fun handleDepositCommand(command: DepositAmountCommand): List<Event> =
        listOf(AmountDepositedEvent(command.amount, command.date))

}
