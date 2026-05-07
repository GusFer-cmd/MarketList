package com.example.marketlist.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketlist.Database.ItemRepository

class ItemViewModelFactory(
    private val repository: ItemRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(ItemViewModel::class.java) -> {
                ItemViewModel(repository) as T
            }

            modelClass.isAssignableFrom(AddItemViewModel::class.java) -> {
                AddItemViewModel(repository) as T
            }

            modelClass.isAssignableFrom(EditItemViewModel::class.java) -> {
                EditItemViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}