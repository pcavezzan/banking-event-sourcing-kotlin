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
            Date        Amount  Balance
            24.12.2015   +500   500
            23.8.2016    -100   400
        """.trimIndent()
        )
    }

    private fun anAccount(): Account {
        val availableDates = Stack<LocalDate>()
        availableDates.push(LocalDate.of(2015, Month.DECEMBER, 23))
        availableDates.push(LocalDate.of(2016, Month.DECEMBER, 23))
        return Account(
            CommandFactory(FakeTime(availableDates)),
            AmountCommandDecision(),
            InMemoryEventStore()
        )
    }


}