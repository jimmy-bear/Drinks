package com.litto.drinks.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.litto.drinks.data.DataRepository

class DrinkViewModel(
    private var repository: DataRepository
) : ViewModel() {
    val categories : LiveData<List<String>> = repository.getCategories()
    val glasses : LiveData<List<String>> = repository.getGlasses()
}