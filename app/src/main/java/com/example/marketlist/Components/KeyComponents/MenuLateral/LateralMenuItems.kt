package com.example.marketlist.Components.KeyComponents.MenuLateral

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketlist.Components.NavGraph.Screen
import com.example.marketlist.R
import com.example.marketlist.ui.theme.ArchivoBlackFamily
import com.example.marketlist.ui.theme.BluePrimary

@Composable
fun LateralMenuItems(
    onNavigate: (String) -> Unit,
    closeDrawer: suspend () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(290.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        Row(modifier = Modifier
                .fillMaxWidth()
                .background(BluePrimary)
                .padding(20.dp, 10.dp),
            horizontalArrangement = Arrangement.Start

        ) {
            Text(
                text = "Menu",
                fontWeight = FontWeight.Bold,
                fontFamily = ArchivoBlackFamily,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
           modifier = Modifier
               .fillMaxWidth()
               .padding(20.dp, 0.dp)
        ) {
            Column (
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                LateralMenuItem(
                    text = "Home",
                    icon = R.drawable.baseline_home_24,
                    onClick = {
                        onNavigate(Screen.Home.route)
                    }
                )

                LateralMenuItem(
                    text = "Lista Inteligente",
                    icon = R.drawable.outline_assignment_add_24,
                    onClick = {
                        onNavigate(Screen.SmartInput.route)
                    }

                )
            }
        }
    }
}