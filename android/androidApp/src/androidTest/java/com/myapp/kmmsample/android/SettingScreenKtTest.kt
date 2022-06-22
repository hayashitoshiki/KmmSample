package com.myapp.kmmsample.android

import org.junit.After
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.myapp.component.component.TabItemTag
import com.myapp.presentation.screen.SettingScreen
import com.myapp.presentation.screen.SettingScreenItemTag
import com.myapp.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * 設定画面　画面動作仕様
 */
@ExperimentalPagerApi
@HiltAndroidTest
class SettingScreenKtTest {

   @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val inputWalletTags = listOf(
        SettingScreenItemTag.InputWalletGmt,
        SettingScreenItemTag.InputWalletGst,
        SettingScreenItemTag.InputWalletSol,
        SettingScreenItemTag.InputWalletUsdc,
        SettingScreenItemTag.InputWalletGem,
        SettingScreenItemTag.InputWalletShoebox,
        SettingScreenItemTag.InputWalletSneaker
    )
    private val inputSpendingTags = listOf(
        SettingScreenItemTag.InputSpendingGmt,
        SettingScreenItemTag.InputSpendingGst,
        SettingScreenItemTag.InputSpendingSol,
        SettingScreenItemTag.InputSpendingGem,
        SettingScreenItemTag.InputSpendingShoebox,
        SettingScreenItemTag.InputSpendingSneaker
    )
    private val inputRateTags = listOf(
        SettingScreenItemTag.InputRateGmt,
        SettingScreenItemTag.InputRateGst,
        SettingScreenItemTag.InputRateSol,
        SettingScreenItemTag.InputRateUsdc,
        SettingScreenItemTag.InputRateGem,
        SettingScreenItemTag.InputRateShoebox,
        SettingScreenItemTag.InputRateSneaker
    )

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        composeTestRule.setContent {
            SettingScreen(viewModel())
        }
    }

    @After
    fun tearDown() {
    }

    // region 入力値保持

    /**
     * Wallet入力欄内容保持
     *
     * 動作：Wallet入力内容を変更してタブ切り替え
     *
     * 期待結果
     * ・Walletタブを開いた時、入力内容が保持されていること
     * ・Spendingタブを開いた時、入力欄がすべて初期値(0)であること
     * ・Rateタブを開いた時、入力欄がすべて初期値(0)であること
     */
    @Test
    fun changeWalletInput() {
        // 実施
        inputSpendingTags.forEachIndexed { index, settingScreenItemTag ->
            composeTestRule.onNodeWithTag(settingScreenItemTag.value).performTextInput(index.toString())
        }

        // 確認
        composeTestRule.onNodeWithTag(TabItemTag.Wallet.value, useUnmergedTree = true).performClick()
        inputWalletTags.forEach{
            composeTestRule.onNodeWithTag(it.value).assert(hasText("0"))
        }
        composeTestRule.onNodeWithTag(TabItemTag.Rate.value, useUnmergedTree = true).performClick()
        inputRateTags.forEach{
            composeTestRule.onNodeWithTag(it.value).assert(hasText("0"))
        }
        composeTestRule.onNodeWithTag(TabItemTag.Spending.value, useUnmergedTree = true).performClick()
        inputSpendingTags.forEachIndexed { index, settingScreenItemTag ->
            composeTestRule.onNodeWithTag(settingScreenItemTag.value).assert(hasText("0$index"))
        }
    }

    /**
     * Spending入力欄内容保持
     *
     * 動作：Spending入力内容を変更してタブ切り替え
     *
     * 期待結果
     * ・Walletタブを開いた時、入力欄がすべて初期値(0)であること
     * ・Spendingタブを開いた時、入力内容が保持されていること
     * ・Rateタブを開いた時、入力欄がすべて初期値(0)であること
     */
    @Test
    fun changeSpendingInput() {
        // 実施
        composeTestRule.onNodeWithTag(TabItemTag.Wallet.value, useUnmergedTree = true).performClick()
        inputWalletTags.forEachIndexed { index, settingScreenItemTag ->
            composeTestRule.onNodeWithTag(settingScreenItemTag.value).performTextInput(index.toString())
        }

        // 確認
        composeTestRule.onNodeWithTag(TabItemTag.Spending.value, useUnmergedTree = true).performClick()
        inputSpendingTags.forEach {
            composeTestRule.onNodeWithTag(it.value).assert(hasText("0"))
        }
        composeTestRule.onNodeWithTag(TabItemTag.Wallet.value, useUnmergedTree = true).performClick()
        inputWalletTags.forEachIndexed { index, settingScreenItemTag ->
            composeTestRule.onNodeWithTag(settingScreenItemTag.value).assert(hasText("0$index"))
        }
        composeTestRule.onNodeWithTag(TabItemTag.Rate.value, useUnmergedTree = true).performClick()
        inputRateTags.forEach{
            composeTestRule.onNodeWithTag(it.value).assert(hasText("0"))
        }
    }

    /**
     * Rate入力欄内容保持
     *
     * 動作：Rate入力内容を変更してタブ切り替え
     *
     * 期待結果
     * ・Walletタブを開いた時、入力欄がすべて初期値(0)であること
     * ・Spendingタブを開いた時、入力欄がすべて初期値(0)であること
     * ・Rateタブを開いた時、入力内容が保持されていること
     */
    @Test
    fun changeRateInput() {
        // 実施
        composeTestRule.onNodeWithTag(TabItemTag.Rate.value, useUnmergedTree = true).performClick()
        inputRateTags.forEachIndexed { index, settingScreenItemTag ->
            composeTestRule.onNodeWithTag(settingScreenItemTag.value).performTextInput(index.toString())
        }

        // 確認
        composeTestRule.onNodeWithTag(TabItemTag.Spending.value, useUnmergedTree = true).performClick()
        inputSpendingTags.forEach {
            composeTestRule.onNodeWithTag(it.value).assert(hasText("0"))
        }
        composeTestRule.onNodeWithTag(TabItemTag.Wallet.value, useUnmergedTree = true).performClick()
        inputWalletTags.forEach {
            composeTestRule.onNodeWithTag(it.value).assert(hasText("0"))
        }
        composeTestRule.onNodeWithTag(TabItemTag.Rate.value, useUnmergedTree = true).performClick()
        inputRateTags.forEachIndexed { index, settingScreenItemTag ->
            composeTestRule.onNodeWithTag(settingScreenItemTag.value).assert(hasText("0$index"))
        }
    }
    // endregion

}
