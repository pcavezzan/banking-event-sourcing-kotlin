package com.foncierenumerique.kata.banking

interface EventStore {
    fun saveAll(events: List<Event>)
    fun findAll(): List<Event>
}