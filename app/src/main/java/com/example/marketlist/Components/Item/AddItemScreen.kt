package com.example.marketlist.Components.Item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketlist.Components.Category.CategoryItem
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.ViewModels.ItemViewModel
import com.example.marketlist.ui.theme.InterFamily
import com.example.marketlist.ui.theme.White
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.marketlist.Components.KeyComponents.Container
import com.example.marketlist.Components.KeyComponents.ToolBar
import com.example.marketlist.Components.NavGraph.Screen
import com.example.marketlist.R
import com.example.marketlist.UI_Event.UiEvent
import com.example.marketlist.ViewModels.AddItemViewModel
import com.example.marketlist.ui.theme.Black
import com.example.marketlist.ui.theme.GraySecondary
import com.example.marketlist.ui.theme.LightBluePrimary
import com.example.marketlist.ui.theme.LightBlueSecondary

@Composable
fun AddItemScreen(
    viewmodel: AddItemViewModel,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit
) {
    val shape = CircleShape

    val focusManager = LocalFocusManager.current

    val state by viewmodel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewmodel.resetState()
    }

    LaunchedEffect(Unit) {
        viewmodel.event.collect { event ->
            when (event) {
                is UiEvent.NavigateHome -> {
                    onNavigateHome()
                }

                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost((snackbarHostState)) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            Container {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    focusManager.clearFocus()
                                }
                            )
                        }
                ) {

                    ToolBar(
                        onCreateClick = {
                            viewmodel.create()
                        },
                        onBackClick = {
                            onNavigateBack()
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Nome do Item",
                        fontFamily = InterFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 32.82.sp,
                        letterSpacing = 0.sp,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = state.name,
                        onValueChange = { viewmodel.onNameChange(it) },
                        isError = state.nameError != null,
                        placeholder = { Text("Suco de Laranja...") },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(53.dp),
                    )

                    state.nameError?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Selecione a Categoria",
                        fontFamily = InterFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 32.82.sp,
                        letterSpacing = 0.sp,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(CategoryEnum.values()) { item ->
                            CategoryItem(
                                category = item,
                                isSelected = state.category == item,
                                onClick = {viewmodel.onCategoryChange(item)}
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Qual é a quantidade?",
                        fontFamily = InterFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 32.82.sp,
                        letterSpacing = 0.sp,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .background(LightBlueSecondary, RoundedCornerShape(12.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            FloatingActionButton (
                                onClick = {
                                    if (state.quantity > 1) {
                                        viewmodel.onQuantityChange(state.quantity - 1)
                                    }
                                },
                                containerColor = White,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .border(1.dp, LightBluePrimary, shape)
                                    .shadow(
                                        elevation = 8.dp,
                                        shape = shape,
                                        clip = false
                                    ),
                                shape = shape

                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.minus),
                                    contentDescription = "Botão de subtração",
                                    tint = LightBluePrimary,
                                )
                            }

                            Column (
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${state.quantity}",
                                    fontFamily = InterFamily,
                                    fontSize = 31.sp,
                                    fontWeight = FontWeight.W600,
                                    lineHeight = 31.sp,
                                    color = Black
                                )

                                Text(
                                    text = "UND.",
                                    fontFamily = InterFamily,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.W600,
                                    lineHeight = 19.sp,
                                    color = GraySecondary
                                )
                            }

                            FloatingActionButton (
                                onClick = { viewmodel.onQuantityChange(state.quantity + 1) },
                                containerColor = White,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .border(1.dp, LightBluePrimary, shape)
                                    .shadow(
                                        elevation = 8.dp,
                                        shape = shape,
                                        clip = false
                                    ),
                                shape = shape
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.plus),
                                    contentDescription = "Botão de adição",
                                    tint = LightBluePrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}