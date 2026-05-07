package com.example.marketlist.Components.NavGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marketlist.Components.HomeScreen
import com.example.marketlist.Components.Item.AddItemScreen
import com.example.marketlist.Components.Item.EditItemScreen
import com.example.marketlist.Components.SmartInput.SmartInputScreen
import com.example.marketlist.ViewModels.AddItemViewModel
import com.example.marketlist.ViewModels.EditItemViewModel
import com.example.marketlist.ViewModels.ItemViewModel

@Composable
fun NavGraph(
    modifier: Modifier,
    viewmodel: ItemViewModel,
    addViewModel: AddItemViewModel,
    editViewModel: EditItemViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {

            HomeScreen(
                viewmodel = viewmodel,
                onAddClick = {
                    navController.navigate(Screen.AddItem.route) },
                onEditClick = { id ->
                    navController.navigate(Screen.EditItem.createRoute(id)) },
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Screen.AddItem.route) {

            AddItemScreen(
                viewmodel = addViewModel,
                onNavigateBack = {
                    navController.popBackStack() },
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.EditItem.route) { backStackEntry ->

            val id = backStackEntry.arguments?.getString("id")
                ?: return@composable

            EditItemScreen(
                itemId = id,
                viewmodel = editViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.SmartInput.route) {

            SmartInputScreen(
                viewmodel = viewmodel,
                onNavigate = { route ->
                    navController.navigate(route) },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}