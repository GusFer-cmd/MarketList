package com.example.marketlist.Components.NavGraph

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object AddItem : Screen("add_item")

    object EditItem : Screen("edit_item/{id}") {
        fun createRoute(id: String) = "edit_item/$id"
    }

    object SmartInput: Screen("smart_input")

}