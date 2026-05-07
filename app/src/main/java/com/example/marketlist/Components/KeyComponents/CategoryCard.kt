    package com.example.marketlist.Components.KeyComponents

    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.KeyboardArrowDown
    import androidx.compose.material.icons.filled.KeyboardArrowUp
    import androidx.compose.material3.Icon
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.marketlist.Components.Item.ItemExibitionField
    import com.example.marketlist.Models.CategoryEnum.CategoryEnum
    import com.example.marketlist.Models.Item.ItemModel
    import com.example.marketlist.ViewModels.ItemViewModel
    import com.example.marketlist.ui.theme.Black
    import com.example.marketlist.ui.theme.White

    @Composable
    fun CategoryCard(
        category: CategoryEnum,
        items: List<ItemModel>,
        modifier: Modifier = Modifier,
        viewModel: ItemViewModel,
        onEditClick: (String) -> Unit,
    ) {

        var clicked by remember { mutableStateOf(false) }
        val shape = RoundedCornerShape(24.dp)

        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape)
                .border(1.dp, category.mainColor, shape = shape)
                .background(category.backgroundColor)
                .padding(10.dp)
                .clickable { clicked = !clicked },
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(category.mainColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(category.icon),
                        contentDescription = category.title,
                        tint = White
                    )
                }

                Spacer(Modifier.width(15.dp))

                Text(
                    text = category.title,
                    color = Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector =
                        if (clicked) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expandir",
                    tint = Black
                )
            }

            if (clicked) {

                Spacer(Modifier.height(10.dp))

                items.forEach { item ->
                    ItemExibitionField(
                        item,
                        toggleDone = { viewModel.toggleDone(item.id) },
                        onEdit = { onEditClick(item.id) },
                        onDelete = { viewModel.delete(item.id)},
                    )
                }
            }
        }
    }