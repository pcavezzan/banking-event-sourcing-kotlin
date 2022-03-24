package com.foncierenumerique.kata.banking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BankingEventSourcingApplication

fun main(args: Array<String>) {
	runApplication<BankingEventSourcingApplication>(*args)
}
