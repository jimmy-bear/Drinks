package com.litto.drinks.data

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.litto.drinks.R
import com.litto.drinks.util.executeThread
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class DrinkStarter(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) = executeThread {
        fillWithStartingData(context)
    }

    @WorkerThread
    private fun fillWithStartingData(context: Context) {
        val dao = DrinkDatabase.getInstance(context).drinkDao()

        try {
            val drinks = loadJsonArray(context)
            if (drinks != null) {
                for (i in 0 until drinks.length()) {
                    val item = drinks.getJSONObject(i)
                    val name = item.getString("name")
                    val category = item.getString("category")
                    val glass = item.getString("glass")
                    val garnish = item.getString("garnish")
                    val ingredients = item.getString("ingredients")
                    val preparation = item.getString("preparation")
                    val time = item.getLong("time")
                    val pass_time = item.getLong("pass_time")
                    val favorites = item.getInt("favorites")

                    val drink = Drink(
                        name = name,
                        category = category,
                        glass = glass,
                        garnish = garnish,
                        ingredients = ingredients,
                        preparation = preparation,
                        time = time,
                        passTime = pass_time,
                        favorite = if (favorites == 0) false else true
                    )
                    dao.insert(drink)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJsonArray(context: Context): JSONArray? {
        val inputStream = context.resources.openRawResource(R.raw.drinks)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}