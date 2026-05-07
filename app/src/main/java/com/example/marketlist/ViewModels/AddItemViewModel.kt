package com.example.marketlist.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketlist.Database.ItemRepository
import com.example.marketlist.Exceptions.ItemException
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.Models.Item.ItemModel
import com.example.marketlist.Models.UI_State.AddItemUiState
import com.example.marketlist.UI_Event.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddItemViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddItemUiState())
    val uiState: StateFlow<AddItemUiState> = _uiState

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event

    fun resetState() {
        _uiState.value = AddItemUiState()
    }

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value, nameError = null)
    }

    fun onQuantityChange(value: Int) {
        _uiState.value = _uiState.value.copy(quantity = value, quantityError = null)
    }

    fun onCategoryChange(value: CategoryEnum) {
        _uiState.value = _uiState.value.copy(category = value)
    }

    fun create() {
        val state = _uiState.value

        viewModelScope.launch {
            try {
                if (state.name.isBlank()) throw ItemException.EmptyNameException()
                if (state.quantity < 1) throw ItemException.QuantityLessThanOneException()

                val item = ItemModel(
                    name = state.name,
                    category = state.category,
                    quantity = state.quantity,
                    checked = false
                )

                repository.create(item)
                resetState()

                _event.emit(UiEvent.NavigateHome)

            } catch (e: ItemException) {
                _uiState.value = when (e) {
                    is ItemException.EmptyNameException ->
                        state.copy(nameError = e.message)

                    is ItemException.QuantityLessThanOneException ->
                        state.copy(quantityError = e.message)

                    else -> state
                }
            }
        }
    }
}