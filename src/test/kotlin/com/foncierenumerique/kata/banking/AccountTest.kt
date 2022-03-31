package com.foncierenumerique.kata.banking

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Month
import java.util.*

class AccountTest {

    @Test
    internal fun `should print account statement`() {
        val account = anAccount()
        account.deposit(500)
        account.withdraw(100)

        val printStatement = account.printStatement()

        assertThat(printStatement).isEqualTo(
            """
            Date   Amount   Balance
            24.12.2015   +500   500
            23.8.2016   -100   400
        """.trimIndent()
        )
    }

    @Test
    internal fun `should not be able to withdraw more money than i have`() {
        val account = anAccount()
        account.deposit(500)
        account.withdraw(600)

        val printStatement = account.printStatement()

        assertThat(printStatement).isEqualTo(
            """
            Date   Amount   Balance
            24.12.2015   +500   500
        """.trimIndent()
        )
    }

    @Test
    internal fun `should not be able to withdraw when i have no money`() {
        val account = anAccount()
        account.deposit(500)
        account.withdraw(500)
        account.withdraw(200)

        val printStatement = account.printStatement()

        assertThat(printStatement).isEqualTo(
            """
            Date   Amount   Balance
            24.12.2015   +500   500
            23.8.2016   -500   0
        """.trimIndent()
        )
    }


    private fun anAccount(): Account {
        val availableDates = Stack<LocalDate>()
        availableDates.push(LocalDate.of(2017, Month.DECEMBER, 17))
        availableDates.push(LocalDate.of(2016, Month.AUGUST, 23))
        availableDates.push(LocalDate.of(2015, Month.DECEMBER, 24))
        return Account(
            CommandFactory(FakeTime(availableDates)),
            AmountCommandDecision(),
            InMemoryEventStore(),
            StatementState(DefaultOperationFormatter())
        )
    }


}