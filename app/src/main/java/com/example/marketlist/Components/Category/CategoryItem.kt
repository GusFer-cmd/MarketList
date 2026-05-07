    package com.example.marketlist.Components.Category

    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.material3.Icon
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.marketlist.Models.CategoryEnum.CategoryEnum
    import com.example.marketlist.ui.theme.Gray
    import com.example.marketlist.ui.theme.White

    @Composable
    fun CategoryItem(
        category: CategoryEnum,
        isSelected: Boolean,
        onClick: () -> Unit
    ) {
        val borderColor = if (isSelected) category.pillBorderColor else White
        val shape = CircleShape

        val titleInfo = when(category.name) {
            "FRUITS" -> "Hortaliças"
            "DAIRY" -> "Laticínios"
            "CLEANING" -> "Limpeza"
            "BAKERY" -> "Padaria"
            "MEAT" -> "Proteínas"
            "SNACKS" -> "Lanches"
            else -> category.name
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = shape,
                        clip = false
                    )
                    .clip(shape)
                    .background(category.backgroundColor, shape = shape)
                    .border(2.dp, borderColor, shape)
                    .clickable { onClick() }
            ) {
             Icon(
                 painter = painterResource(category.icon),
                 contentDescription = category.title,
                 tint = category.pillBorderColor
             )
            }

            Text(
                text = titleInfo,
                fontSize = 12.sp,
                color = if (isSelected) borderColor else Gray
            )
        }
    }