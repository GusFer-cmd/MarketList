package com.example.marketlist.UI_Event

sealed class UiEvent {
    object NavigateHome : UiEvent()
    data class ShowSnackbar(val message: String) : UiEvent()
}