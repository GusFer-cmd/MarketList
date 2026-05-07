package com.example.marketlist.Models.UI_State

import com.example.marketlist.Models.Item.ItemModel

data class IAItemUiState (
    val items: List<ItemModel> = emptyList(),
    val aiGeneratedItems: List<ItemModel> = emptyList(),
    val aiLoading: Boolean = false,
    val aiError: String? = null
)