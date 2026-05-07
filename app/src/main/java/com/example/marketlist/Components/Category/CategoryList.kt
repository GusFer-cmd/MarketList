package com.example.marketlist.Components.Category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marketlist.Components.KeyComponents.CategoryCard
import com.example.marketlist.Components.KeyComponents.Container
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.Models.Item.ItemModel
import com.example.marketlist.ViewModels.ItemViewModel
import com.example.marketlist.ui.theme.RedPrimary

@Composable
fun CategoryList(
    groupedItems: Map<CategoryEnum, List<ItemModel>>,
    viewModel: ItemViewModel,
    onEditClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        groupedItems.forEach { (category, items) ->
            item {
                CategoryCard(
                    category = category,
                    items = items,
                    viewModel = viewModel,
                    onEditClick = onEditClick
                )
            }
        }
    }
}

