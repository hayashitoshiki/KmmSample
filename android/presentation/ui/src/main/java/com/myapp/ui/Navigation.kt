package com.myapp.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapp.presentation.screen.HomeScreen
import com.myapp.presentation.screen.SettingScreen
import com.myapp.presentation.screen.SpendingTotalScreen
import com.myapp.presentation.screen.WalletTotalScreen

/**
 * 画面定義
 *
 * @property icon アイコン
 * @property route 遷移パス
 * @property stringResId 画面タイトル
 */
enum class NavigationScreens(
    val icon: ImageVector,
    val route: String,
    @StringRes val stringResId: Int
) {
    HOME_SCREEN(Icons.Filled.Home, "home_route", R.string.title_home),
    SPENDING_TOTAL_SCREEN(Icons.Filled.Paid, "spending_total_route", R.string.title_spending_total),
    WALLET_TOTAL_SCREEN(Icons.Filled.Savings, "wallet_total_route", R.string.title_wallet_total),
    SETTING_SCREEN(Icons.Filled.Settings, "setting_route", R.string.title_setting),
}

/**
 * NavigationHost 画面遷移定義
 *
 * @param navController ナビゲーションAPI
 */
@ExperimentalPagerApi
@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.HOME_SCREEN.route
    ) {
        composable(route = NavigationScreens.HOME_SCREEN.route) { HomeScreen(hiltViewModel()) }
        composable(route = NavigationScreens.SPENDING_TOTAL_SCREEN.route) { SpendingTotalScreen(hiltViewModel()) }
        composable(route = NavigationScreens.WALLET_TOTAL_SCREEN.route) { WalletTotalScreen(hiltViewModel()) }
        composable(route = NavigationScreens.SETTING_SCREEN.route) { SettingScreen(hiltViewModel()) }
    }
}
