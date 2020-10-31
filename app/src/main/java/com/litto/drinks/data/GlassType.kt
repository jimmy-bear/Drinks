package com.litto.drinks.data

enum class GlassType (val value: String) {
    HIGH_BALL("highball"),
    MARTINI("martini"),
    CHAMPAGNE_FLUTE("champagne-flute"),
    OLD_FASHIONED("old-fashioned"),
    HOT_DRINK("hot-drink"),
    HURRICANE("hurricane"),
    MARGARITA("margarita"),
    WHITE_WINE("white-wine");

    companion object {
        fun findByName(name: String?) = when (name) {
            HIGH_BALL.value -> HIGH_BALL
            MARTINI.value -> MARTINI
            CHAMPAGNE_FLUTE.value -> CHAMPAGNE_FLUTE
            OLD_FASHIONED.value -> OLD_FASHIONED
            HOT_DRINK.value -> HOT_DRINK
            HURRICANE.value -> HURRICANE
            MARGARITA.value -> MARGARITA
            WHITE_WINE.value -> WHITE_WINE
            else -> HIGH_BALL
        }
    }
}