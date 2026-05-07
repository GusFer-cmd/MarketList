package com.example.marketlist.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketlist.Database.ItemRepository
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.Models.Item.ItemModel
import com.example.marketlist.Models.UI_State.AddItemUiState
import com.example.marketlist.UI_Event.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditItemViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    private var itemId: String? = null

    private val _uiState = MutableStateFlow(AddItemUiState())
    val uiState: StateFlow<AddItemUiState> = _uiState

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event

    fun loadItem(item: ItemModel) {
        itemId = item.id

        _uiState.value = _uiState.value.copy(
            name = item.name,
            category = item.category,
            quantity = item.quantity,
            checked = item.checked
        )
    }

    suspend fun getById(id: String): ItemModel? {
        return try {
            repository.getById(id)
        } catch (e: Exception) {
            _event.emit(UiEvent.ShowSnackbar("Erro ao buscar item"))
            null
        }
    }

    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value)
    }

    fun onQuantityChange(value: Int) {
        _uiState.value = _uiState.value.copy(quantity = value)
    }

    fun onCategoryChange(value: CategoryEnum) {
        _uiState.value = _uiState.value.copy(category = value)
    }

    fun update() {
        val state = _uiState.value
        val id = itemId ?: return

        viewModelScope.launch {
            try {
                val item = ItemModel(
                    id = id,
                    name = state.name,
                    category = state.category,
                    quantity = state.quantity,
                    checked = state.checked
                )

                repository.update(item)

                _event.emit(UiEvent.NavigateHome)

            } catch (e: Exception) {
                _event.emit(UiEvent.ShowSnackbar("Erro ao atualizar"))
            }
        }
    }
}