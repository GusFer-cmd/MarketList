package com.example.marketlist.Components.KeyComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketlist.ViewModels.ItemViewModel
import com.example.marketlist.ui.theme.GraySecondary
import com.example.marketlist.ui.theme.InterFamily
import com.example.marketlist.ui.theme.White

@Composable
fun FormItem(
) {

    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Nome do item",
            fontFamily = InterFamily,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            lineHeight = 32.82.sp,
            letterSpacing = 0.sp
        )

        Spacer(Modifier.height(5.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(
                text = "Suco de laranja...",
                fontFamily = InterFamily,
                color = GraySecondary
            )},
            modifier = Modifier.fillMaxWidth()
        )
    }
}