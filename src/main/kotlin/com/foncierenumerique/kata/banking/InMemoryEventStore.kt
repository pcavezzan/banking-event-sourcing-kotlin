package com.foncierenumerique.kata.banking

class InMemoryEventStore : EventStore {
    private val storedEvents: MutableList<Event> = mutableListOf()

    override fun saveAll(events: List<Event>) {
        storedEvents.addAll(events)
    }

    override fun findAll(): List<Event> = storedEvents

}