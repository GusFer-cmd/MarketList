package com.example.marketlist.Models.CategoryEnum

import androidx.annotation.ColorRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.marketlist.R
import com.example.marketlist.ui.theme.BluePrimary
import com.example.marketlist.ui.theme.BlueSecondary
import com.example.marketlist.ui.theme.BrownPrimary
import com.example.marketlist.ui.theme.BrownSecondary
import com.example.marketlist.ui.theme.GreenPrimary
import com.example.marketlist.ui.theme.GreenSecondary
import com.example.marketlist.ui.theme.OrangePrimary
import com.example.marketlist.ui.theme.OrangeSecondary
import com.example.marketlist.ui.theme.PurplePrimary
import com.example.marketlist.ui.theme.PurpleSecondary
import com.example.marketlist.ui.theme.RedPrimary
import com.example.marketlist.ui.theme.RedSecondary

enum class CategoryEnum (
    val title: String,
    val mainColor: Color,
    val backgroundColor: Color,
    val pillBorderColor: Color,
    val icon: Int
)
{
    FRUITS (
        title = "Frutas e Vegetais",
        mainColor = GreenPrimary,
        backgroundColor = GreenSecondary,
        pillBorderColor = GreenPrimary,
        icon = R.drawable.lucide_leaf,
    ),

    DAIRY (
        title = "Laticínios e Ovos",
        mainColor = BrownPrimary,
        backgroundColor = BrownSecondary,
        pillBorderColor = BrownPrimary,
        icon = R.drawable.lucide_egg,
    ),

    CLEANING (
        title = "Itens de Limpeza",
        mainColor = BluePrimary,
        backgroundColor = BlueSecondary,
        pillBorderColor = BluePrimary,
        icon = R.drawable.lucide_bubbles,
    ),

    BAKERY (
        title = "Padaria",
        mainColor = OrangePrimary,
        backgroundColor = OrangeSecondary,
        pillBorderColor = OrangePrimary,
        icon = R.drawable.lucide_croissant,
    ),

    MEAT (
        title = "Proteínas",
        mainColor = RedPrimary,
        backgroundColor = RedSecondary,
        pillBorderColor = RedPrimary,
        icon = R.drawable.lucide_beef,
    ),

    SNACKS (
        title = "Lanches",
        mainColor = PurplePrimary,
        backgroundColor = PurpleSecondary,
        pillBorderColor = PurplePrimary,
        icon = R.drawable.lucide_donut,
    )
}