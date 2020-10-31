package com.litto.drinks.list

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litto.drinks.data.DataRepository
import java.lang.IllegalStateException

class DrinkListViewModelFactory(
    private val repository: DataRepository?,
    private val filterBy: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }

    companion object {
        fun createFactory(activity: Activity) : DrinkListViewModelFactory {
            val context = activity.applicationContext ?: throw IllegalStateException("No application")
            //TODO: replace filterBy
            return DrinkListViewModelFactory(DataRepository.getInstance(context), "category")
        }
    }
}