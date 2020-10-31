package com.litto.drinks.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Drink(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = DrinkNames.COL_NAME)
    val name: String,
    @ColumnInfo(name = DrinkNames.COL_CATEGORY)
    val category: String,
    @ColumnInfo(name = DrinkNames.COL_GLASS)
    val glass: String,
    @ColumnInfo(name = DrinkNames.COL_GARNISH)
    val garnish: String?,
    @ColumnInfo(name = DrinkNames.COL_INGREDIENTS)
    val ingredients: String,
    @ColumnInfo(name = DrinkNames.COL_PREPARATION)
    val preparation: String,
    @ColumnInfo(name = DrinkNames.COL_TIME)
    val time: Long,
    @ColumnInfo(name = DrinkNames.COL_PASS_TIME)
    val passTime: Long,
    @ColumnInfo(name = DrinkNames.COL_FAVORITES)
    val favorite: Boolean = false
)