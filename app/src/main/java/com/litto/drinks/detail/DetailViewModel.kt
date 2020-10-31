package com.litto.drinks.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.litto.drinks.data.DataRepository
import com.litto.drinks.data.Drink
import com.litto.drinks.data.GlassType

class DetailViewModel(private val repository: DataRepository,
                      private val drinkName: String) : ViewModel(){
    val drink: LiveData<Drink> = repository.getDrink(drinkName)
    private val detail = MutableLiveData<DetailDataModel>()

    val glassImage: LiveData<DetailDataModel>
        get() = detail

    fun setFavorite() {
        //TODO:
        repository.updateFavorite(drinkName)
    }

    fun setGlassImage(glass: String) {
        val glassType = GlassType.findByName(glass)
        detail.value = DetailDataModel(glassType.value)
    }
}