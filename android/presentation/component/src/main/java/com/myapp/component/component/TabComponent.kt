package com.myapp.component.component

// ========================================
// 　　　　　TabコンテンツComponent
// ========================================

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.myapp.common.SealedClassEnumExtension
import com.myapp.common.SealedClassEnumWithName
import com.myapp.component.theme.TextColor
import kotlinx.coroutines.launch


// ======================================== //
// 　　　　　Tabリスト                         //
// ======================================== //

/**
 * タブリスト（基底クラス）
 *
 * @property icon アイコン
 * @property title タイトル
 * @property screen 表示する画面
 */
abstract class TabItem(val icon: ImageVector, val title: String, val screen: @Composable () -> Unit)


/**
 * 設定画面_各ウォレットタブ
 */
sealed class SettingWalletTabItem(icon: ImageVector, title: String, screen: @Composable () -> Unit)
    : TabItem(icon, title, screen), SealedClassEnumWithName {
    class SettingSpending(screen: @Composable () -> Unit) : SettingWalletTabItem(Icons.Filled.Home, "spending", { screen() })
    class SettingWallet(screen: @Composable () -> Unit) : SettingWalletTabItem(Icons.Filled.ShoppingCart, "wallet", { screen() })
    class SettingRate(screen: @Composable () -> Unit) : SettingWalletTabItem(Icons.Filled.Settings, "rate", { screen() })

    companion object : SealedClassEnumExtension<SettingWalletTabItem>
}


// ========================================  //
// 　　　　　公開用タブ画面Component             //
// ======================================== //

/**
 * サンプルタブ画面Component
 *
 */
@ExperimentalPagerApi
@Composable
fun Sample1TabComponent(
    SettingSpendingScreen: @Composable () -> Unit,
    SettingWalletScreen: @Composable () -> Unit,
    SettingRateScreen: @Composable () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val tabs = listOf(
        SettingWalletTabItem.SettingSpending(SettingSpendingScreen),
        SettingWalletTabItem.SettingWallet(SettingWalletScreen),
        SettingWalletTabItem.SettingRate(SettingRateScreen)
    )

    // レイアウト
    Column {
        CustomTabBar(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}


// ======================================== //
// 　　　　　内部用Util Tab Component          //
// ======================================== //

/**
 * カスタムレイアウトタブバー
 *
 * @param tabs タブリスト
 * @param pagerState pager
 */
@ExperimentalPagerApi
@Composable
private fun CustomTabBar(tabs: List<TabItem>, pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(Modifier.pagerTabIndicatorOffset(pagerState, tabPositions))
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val isSelected = pagerState.currentPage == index
            val tabColor = if (isSelected) { Color.White } else { Color.Transparent }
            val textColor = if (isSelected) { TextColor.darkSecondary } else { TextColor.lightSecondary }
            val roundedCornerShape = RoundedCornerShape( 30.dp)

            Tab(
                selected = isSelected,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            ) {
                // この中でタブのレイアウトを作成
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                       .fillMaxWidth()
                        .clip(roundedCornerShape)
                        .background(color = tabColor)
                        .border(
                            width = 1.dp,
                            color = textColor,
                            shape = roundedCornerShape
                        )
                ) {
                    Text(
                        text = tab.title,
                        color = textColor,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.align(Alignment.Center).padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

/**
 * タブコンテンツ
 *
 * @param tabs タブリスト
 * @param pagerState タブのインデックス
 */
@ExperimentalPagerApi
@Composable
private fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}
