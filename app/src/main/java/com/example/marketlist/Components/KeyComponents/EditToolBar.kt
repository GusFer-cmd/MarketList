package com.example.marketlist.Components.KeyComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketlist.R
import com.example.marketlist.ui.theme.Gray
import com.example.marketlist.ui.theme.GreenPrimary
import com.example.marketlist.ui.theme.GreenSecondary
import com.example.marketlist.ui.theme.InterFamily
import com.example.marketlist.ui.theme.White

@Composable
fun EditToolBar(
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .border(2.dp, Gray, CircleShape)
                .background(White, CircleShape)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.arrowback),
                contentDescription = "Voltar",
                tint = Gray,
                modifier = Modifier.size(14.dp)
            )
        }

        Text(
            text = "Editar item",
            fontWeight = FontWeight.W600,
            fontFamily = InterFamily,
            fontSize = 16.sp,
            lineHeight = 32.82.sp,
            letterSpacing = 0.sp
        )

        Box(
            modifier = Modifier
                .size(35.dp)
                .border(2.dp, GreenPrimary, CircleShape)
                .background(GreenSecondary, CircleShape)
                .clickable { onEditClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.check),
                contentDescription = "Confirmar",
                tint = GreenPrimary,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}