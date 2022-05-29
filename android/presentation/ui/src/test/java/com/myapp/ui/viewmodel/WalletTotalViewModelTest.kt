package com.myapp.ui.viewmodel

import com.myapp.component.component.ChartValue
import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.model.value.LegalTender
import com.myapp.presentation.extension.chartColor
import com.myapp.test.MockUtil
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Wallet管理画面　UIロジック使用
 *
 */
class WalletTotalViewModelTest {

    private var state = WalletContract.State()

    private lateinit var viewModel: WalletTotalViewModel
    private lateinit var  coinUseCase: CoinUseCase

    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(coroutineDispatcher)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        coinUseCaseAllTrue()
        initViewModel()
    }

    private fun coinUseCaseAllTrue() {
        coinUseCase = mockk<CoinUseCase>().also { useCase ->
            coEvery { useCase.getWalletCoin() } returns MockUtil.getWalletCoin()
            MockUtil.getAllAsset().values().map {
                coEvery { useCase.changeStableRate(it, any()) } returns it.value * 5
            }
        }
    }
    private fun initViewModel() {
        viewModel = WalletTotalViewModel(coinUseCase)
        state = viewModel.state.value
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * 実行結果比較
     *
     * @param state Stateの期待値
     * @param effect Effectの期待値
     */
    @ExperimentalCoroutinesApi
    private fun result(
        state: WalletContract.State,
        effect: WalletContract.Effect? = null
    ) {
        val resultState = viewModel.state.value
        var resultEffect: WalletContract.Effect? = null
        viewModel.effect
            .onEach { resultEffect = it }
            .launchIn(testScope)
        // 比較
        assertEquals(state, resultState)
        assertEquals(effect, resultEffect)
    }

    // region 初期化処理

    /**
     * 初期化処理
     *
     * 条件：なし
     * 期待結果：チャート値に取得したWalletの値が設定されること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun init() {
        // 期待結果
        val chartValues = coinUseCase.getWalletCoin().values().map {
            ChartValue(
                it.type().label,
                it.value.toString(),
                coinUseCase.changeStableRate(it, LegalTender.JPY),
                it.type().chartColor()
            )
        }
        val resultState = state.copy(
            chartValue = chartValues
        )

        // 実施

        // 比較
        result(resultState)
    }

    // endregion
}