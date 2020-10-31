package com.litto.drinks.detail

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litto.drinks.data.DataRepository
import com.litto.drinks.list.DrinkListViewModelFactory
import java.lang.IllegalStateException

class DetailViewModelFactory(private val repository: DataRepository?,
                             private val drinkName: String) : ViewModelProvider.Factory{

    companion object {
        fun createFactory(activity: Activity) : DrinkListViewModelFactory {
            val context = activity.applicationContext ?: throw IllegalStateException("No application")
            //TODO
            return DrinkListViewModelFactory(DataRepository.getInstance(context), "drinkName")
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }
}