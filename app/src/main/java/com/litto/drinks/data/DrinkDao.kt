package com.litto.drinks.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery

interface DrinkDao {

    fun getRandomDrink(): Drink

    fun getAll(query: SupportSQLiteQuery): DataSource.Factory<Int, Drink>

    fun getDrink(name: String): LiveData<Drink>

    fun updateFavorite(name: String)

    @Query("select * from drink where category = :category and glass = :glass and time = :makeTime")
    fun searchDrinks(category: String, glass: String, makeTime: Int): DataSource.Factory<Int, Drink>

    @Query("select * from drink where glass = :glass and time = :makeTime")
    fun searchDrinks(glass: String, makeTime: Int): DataSource.Factory<Int, Drink>

    @Query("select * from drink where time > :makeTime")
    fun searchDrinks(makeTime: Int): DataSource.Factory<Int, Drink>

    @Query("select * from drink where category = :category")
    fun searchDrinks(category: String): DataSource.Factory<Int, Drink>

    @Query("select distinct category from drink")
    fun getDrinkCategories(): LiveData<List<String>>

    @Query("select distinct glass from drink")
    fun getDrinkGlasses(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg drink: Drink)

    @Delete
    fun delete(drink: Drink)
}