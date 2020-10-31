package com.litto.drinks.util

class Event<out T>(private val content: T) {

    var eventHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (eventHandled) {
            null
        } else {
            eventHandled = true
            content
        }
    }
}
