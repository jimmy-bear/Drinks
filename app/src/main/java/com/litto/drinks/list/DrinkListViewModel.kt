package com.litto.drinks.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.litto.drinks.data.DataRepository
import com.litto.drinks.data.Drink
import com.litto.drinks.util.Event

class DrinkListViewModel (private val repository: DataRepository, val filterBy: String) : ViewModel() {
    private val favoriteFilter = MutableLiveData<Boolean>()
    private val undoDelete = MutableLiveData<Event<Drink>>()
    var drinkFilter = MutableLiveData<FilterType>()
    var searchCategory: String = ""
    var searchGlass: String = ""

    fun getSearch() : LiveData<PagedList<Drink>> {
        drinkFilter.value = FilterType.SEARCH_RESULTS
        return repository.searchDrinks(searchCategory, searchGlass, -1)
    }

    fun getAll() : LiveData<PagedList<Drink>> {
        val drinks = when {
            searchCategory.length > 0 -> repository.searchDrinks(searchCategory, searchGlass, -1)
            searchGlass.length > 0 -> repository.searchDrinks(searchCategory, searchGlass, -1)
            else -> repository.getDrinks()
        }
        return drinks
    }

    init {
        drinkFilter.value = FilterType.NONE
        favoriteFilter.value = false
    }

    val undo: LiveData<Event<Drink>>
        get() = undoDelete

    fun toggleFilter() {
        when (drinkFilter.value) {
            FilterType.NONE -> drinkFilter.value = FilterType.FAVORITES
            FilterType.FAVORITES -> drinkFilter.value = FilterType.SEARCH_RESULTS
            FilterType.SEARCH_RESULTS -> drinkFilter.value = FilterType.NONE
            else -> drinkFilter.value = FilterType.FAVORITES
        }
    }

    fun delete(drink: Drink) {
        repository.delete(drink)
        undoDelete.value = Event(drink)
    }
}

enum class FilterType {
    NONE, FAVORITES, SEARCH_RESULTS
}
