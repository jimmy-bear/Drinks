package com.litto.drinks.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object {

        @Volatile
        private var instance: DrinkDatabase? = null

        fun getInstance(context: Context): DrinkDatabase {
            return Room.databaseBuilder(context, DrinkDatabase::class.java, "tea.db")
                .build()
        }
    }
}