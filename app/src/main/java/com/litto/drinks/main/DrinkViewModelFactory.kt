package com.litto.drinks.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litto.drinks.data.DataRepository
import java.lang.IllegalStateException

class DrinkViewModelFactory(private val repository: DataRepository?)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }

    companion object {
        fun createFactory(activity: Activity) : DrinkViewModelFactory {
            val context = activity.applicationContext ?: throw IllegalStateException("No application")
            return DrinkViewModelFactory(DataRepository.getInstance(context))
        }
    }
}