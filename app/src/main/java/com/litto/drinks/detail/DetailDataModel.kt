package com.litto.drinks.detail

import com.litto.drinks.R
import com.litto.drinks.data.GlassType

class DetailDataModel (private val glassType: String) {

    val imageId: Int
        get() = getGlassImage(glassType)

    /**
     * Supported background toolbar images
     *
     * @param type of glass e.g. "high ball"
     * @return resource identifier for drawable
     */
    private fun getGlassImage(type: String): Int {
        return when (type) {
            GlassType.HIGH_BALL.value -> R.drawable.glass_highball
            GlassType.MARTINI.value -> R.drawable.glass_martini
            GlassType.CHAMPAGNE_FLUTE.value -> R.drawable.glass_champagne_flute
            GlassType.OLD_FASHIONED.value -> R.drawable.glass_old_fashioned
            GlassType.HOT_DRINK.value -> R.drawable.glass_hot_drink
            GlassType.HURRICANE.value -> R.drawable.glass_hurricane
            GlassType.MARGARITA.value -> R.drawable.glass_margarita
            GlassType.WHITE_WINE.value -> R.drawable.glass_white_wine
            else -> R.drawable.glass_highball
        }
    }
}
