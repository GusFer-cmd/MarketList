package com.example.marketlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.marketlist.Components.NavGraph.NavGraph
import com.example.marketlist.Database.ItemRepository
import com.example.marketlist.ViewModels.AddItemViewModel
import com.example.marketlist.ViewModels.EditItemViewModel
import com.example.marketlist.ViewModels.ItemViewModel
import com.example.marketlist.ViewModels.ItemViewModelFactory
import com.example.marketlist.ui.theme.MarketListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = MainApplication.itemDatabase.getItemDAO()
        val repository = ItemRepository(dao)
        val factory = ItemViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ItemViewModel::class.java]

        val addViewModel = ViewModelProvider(this, factory)[AddItemViewModel::class.java]

        val editViewModel = ViewModelProvider(this, factory)[EditItemViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MarketListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(modifier = Modifier
                        .padding(innerPadding),
                        viewModel,
                        addViewModel,
                        editViewModel
                    )
                }
            }
        }
    }
}
