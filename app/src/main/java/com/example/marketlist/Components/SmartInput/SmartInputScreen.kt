package com.example.marketlist.Components.SmartInput

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketlist.Components.KeyComponents.Container
import com.example.marketlist.Components.KeyComponents.MenuLateral.LateralMenu
import com.example.marketlist.Components.KeyComponents.MenuLateral.LateralMenuItems
import com.example.marketlist.R
import com.example.marketlist.ViewModels.ItemViewModel
import com.example.marketlist.ui.theme.ArchivoBlackFamily
import com.example.marketlist.ui.theme.GreenPrimary
import com.example.marketlist.ui.theme.InterFamily
import com.example.marketlist.ui.theme.RedPrimary
import kotlinx.coroutines.launch

@Composable
fun SmartInputScreen(
    viewmodel: ItemViewModel,
    onNavigate: (String) -> Unit,
    onNavigateBack: () -> Unit,
) {

    val state by viewmodel.state.collectAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewmodel.resetAIState()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerShape = RectangleShape) {
                LateralMenuItems(
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

        Scaffold { padding ->

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
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    focusManager.clearFocus()
                                }
                            }
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Box(modifier = Modifier
                                .fillMaxWidth()) {
                                LateralMenu(
                                    openDrawer = {
                                        scope.launch { drawerState.open() }
                                    }
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_approval_delegation_24),
                                    contentDescription = null,
                                )

                                Spacer(modifier = Modifier.width(15.dp))

                                Text(
                                    text = "Gere sua lista de compras!",
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = InterFamily,
                                    fontSize = 20.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "Descreva o que você precisa",
                                fontSize = 14.sp,
                                fontFamily = ArchivoBlackFamily,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            TextField(
                                value = viewmodel.aiPrompt,
                                onValueChange = { viewmodel.onAiPromptChange(it) },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                placeholder = { Text("Churrasco para 5 pessoas...") }
                            )

                            Spacer(modifier = Modifier
                                .height(12.dp))

                            Button(
                                onClick = { viewmodel.generateListWithAI() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GreenPrimary,
                                    contentColor = contentColorFor(GreenPrimary)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Gerar lista"
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            if (state.aiLoading) {
                                CircularProgressIndicator()
                            }

                            state.aiError?.let {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = it,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            if (state.aiGeneratedItems.isNotEmpty()) {

                                Text(
                                    text = "Sugestão gerada:",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(max = 250.dp)
                                ) {
                                    items(state.aiGeneratedItems) { item ->
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(12.dp)
                                            ) {
                                                Text(
                                                    text = item.name,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                                Text("Qtd: ${item.quantity}")
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }

                        if (state.aiGeneratedItems.isNotEmpty()) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Button(
                                    onClick = {
                                        viewmodel.confirmGeneratedItems()
                                        onNavigateBack()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GreenPrimary,
                                        contentColor = contentColorFor(GreenPrimary)
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(50.dp)
                                ) {
                                    Text("Salvar")
                                }

                                OutlinedButton(
                                    onClick = { viewmodel.clearGeneratedItems() },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(50.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = RedPrimary
                                    ),
                                    border = BorderStroke(1.dp, RedPrimary)
                                ) {
                                    Text("Cancelar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}