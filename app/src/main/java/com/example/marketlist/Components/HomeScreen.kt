    package com.example.marketlist.Components

    import androidx.compose.foundation.BorderStroke
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.WindowInsets
    import androidx.compose.foundation.layout.consumeWindowInsets
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.statusBars
    import androidx.compose.foundation.layout.systemBars
    import androidx.compose.foundation.layout.windowInsetsPadding
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.AlertDialog
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
    import androidx.compose.material3.DrawerValue
    import androidx.compose.material3.FloatingActionButton
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.ModalDrawerSheet
    import androidx.compose.material3.ModalNavigationDrawer
    import androidx.compose.material3.OutlinedButton
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.SnackbarHost
    import androidx.compose.material3.SnackbarHostState
    import androidx.compose.material3.Text
    import androidx.compose.material3.rememberDrawerState
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.RectangleShape
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import com.example.marketlist.Components.Category.CategoryList
    import com.example.marketlist.Components.KeyComponents.Container
    import com.example.marketlist.Components.KeyComponents.Header
    import com.example.marketlist.Components.KeyComponents.MenuLateral.LateralMenu
    import com.example.marketlist.Components.KeyComponents.MenuLateral.LateralMenuItems
    import com.example.marketlist.Components.KeyComponents.ProgressCard
    import com.example.marketlist.R
    import com.example.marketlist.UI_Event.UiEvent
    import com.example.marketlist.ViewModels.ItemViewModel
    import com.example.marketlist.ui.theme.GreenPrimary
    import com.example.marketlist.ui.theme.RedPrimary
    import kotlinx.coroutines.launch

    @Composable
    fun HomeScreen(
        viewmodel: ItemViewModel,
        onAddClick: () -> Unit,
        onEditClick: (String) -> Unit,
        onNavigate: (String) -> Unit
    ) {

        val total by viewmodel.totalItems.collectAsState(initial = 0)
        val checked by viewmodel.checkedItems.collectAsState(initial = 0)
        val items by viewmodel.itemList.collectAsState(initial = emptyList())
        val groupedItems = items.groupBy { it.category }

        val showDialog = remember { mutableStateOf(false) }

        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val snackbarHostState = remember { SnackbarHostState() }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                shape = RoundedCornerShape(20.dp),

                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    color = Color(0xFFFFF3E0),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "!",
                                color = RedPrimary,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Tem certeza?",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },

                text = {
                    Text(
                        text = "Ao selecionar 'Deletar' a lista será apagada.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },

                confirmButton = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = { showDialog.value = false },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Cancelar")
                        }

                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewmodel.clearAll()
                                showDialog.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenPrimary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Deletar", color = Color.White)
                        }
                    }
                }
            )
        }

        LaunchedEffect(Unit) {
            viewmodel.event.collect { event ->
                when (event) {

                    is UiEvent.ShowSnackbar -> {
                        snackbarHostState.showSnackbar(event.message)
                    }

                    else -> Unit
                }
            }
        }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerShape = RectangleShape,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    LateralMenuItems (
                        onNavigate = {
                            scope.launch { drawerState.close() }
                            onNavigate(it)
                        },
                        closeDrawer = {
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        ) {

            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                },
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onAddClick,
                        containerColor = GreenPrimary
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Adicionar item",
                            tint = Color(0xFFF9F3EF)
                        )
                    }
                }
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
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                LateralMenu(
                                    openDrawer = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                )
                            }

                            Header("Meu Carrinho")

                            ProgressCard(
                                total = total,
                                checked = checked
                            )

                                if (items.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(14.dp))

                                    OutlinedButton(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        onClick = { showDialog.value = true },
                                        colors = outlinedButtonColors(
                                            contentColor = RedPrimary
                                        ),
                                        border = BorderStroke(1.dp, RedPrimary)
                                    ) {

                                        Text(
                                            text = "Limpar Lista"
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(20.dp))

                                    CategoryList(
                                        groupedItems = groupedItems,
                                        viewModel = viewmodel,
                                        onEditClick = onEditClick,
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                if (items.isEmpty()) {

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = "Sua lista está vazia, para começar a adicionar itens clique no botão '+' abaixo",
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                        }
                    }
                }
            }
        }
    }