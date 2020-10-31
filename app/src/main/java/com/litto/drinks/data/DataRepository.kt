package com.litto.drinks.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import com.litto.drinks.util.executeThread
import java.lang.StringBuilder
import java.util.concurrent.Executors

class DataRepository(private val dao: DrinkDao)  {
    fun getDrink(name: String): LiveData<Drink> {
        return dao.getDrink(name)
    }

    fun getSortedDrinks(sort: String, filtByFavorite: Boolean = false): LiveData<PagedList<Drink>> {
        TODO("composite query needed")
    }

    fun getCategories() : LiveData<List<String>> {
        TODO()
    }

    fun getGlasses() : LiveData<List<String>> {
        TODO()
    }

    fun getDrinks(): LiveData<PagedList<Drink>> {
        val query = StringBuilder().apply {
            append("select * from drinks ")
        }
        val drinks = LivePagedListBuilder<Int, Drink>(
            dao.getAll(SimpleSQLiteQuery(query.toString())), 10)
            .build()
        return drinks
    }

    fun updateFavorite(drinkName: String) {
        TODO("Not yet implemented")
    }
    fun delete(drink: Drink) = executeThread {
        dao.delete(drink)
    }

    fun searchDrinks(category: String, glass: String, makeTime: Int): LiveData<PagedList<Drink>> {
        val drinks: LiveData<PagedList<Drink>> = LivePagedListBuilder<Int, Drink>(
            when {
                category.length > 0 -> dao.searchDrinks(category)
                glass.length > 0 -> dao.searchDrinks(glass, 0)
                else ->
                    dao.searchDrinks(category, glass, makeTime)
            },
            10)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setInitialLoadKey(0)
            .build()
        return drinks
    }

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        private const val PAGE_SIZE = 20

        fun getInstance(context: Context): DataRepository? {
            return instance ?: synchronized(DataRepository::class.java) {
                if (instance == null) {
                    val database = DrinkDatabase.getInstance(context)
                    instance = DataRepository(database.drinkDao())
                }
                return instance
            }
        }
    }
}