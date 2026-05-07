package com.example.marketlist.Components.KeyComponents.MenuLateral

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marketlist.R

@Composable
fun LateralMenu(
    openDrawer: () -> Unit
) {
    Icon(
        painter = painterResource(id = R.drawable.outline_menu_24),
        contentDescription = "Menu",
        modifier = Modifier
            .size(25.dp)
            .clickable {
                openDrawer()
            }
    )
}