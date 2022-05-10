package com.myapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapp.component.theme.StepnCacheManagementAndroidTheme
import com.myapp.presentation.HeaderTabRow
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepnManegementApp()
        }
    }
}

@ExperimentalPagerApi
@Composable
fun StepnManegementApp() {
    StepnCacheManagementAndroidTheme {
        val navController = rememberNavController()
        val allScreens = NavigationScreens.values().toList()
        var currentScreen by rememberSaveable { mutableStateOf(NavigationScreens.HOME_SCREEN) }
        Scaffold(
            topBar = {
                HeaderTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.route)
                        currentScreen = screen
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                AppNavHost(navController)
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StepnCacheManagementAndroidTheme {
        val navController = rememberNavController()
        AppNavHost(navController)
    }
}