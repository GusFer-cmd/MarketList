package com.example.marketlist.Models.UI_State

import com.example.marketlist.Models.CategoryEnum.CategoryEnum

data class AddItemUiState(
    val name: String = "",
    val nameError: String? = null,

    val quantity: Int = 1,
    val quantityError: String? = null,

    val category: CategoryEnum = CategoryEnum.FRUITS,
    val checked: Boolean = false
)