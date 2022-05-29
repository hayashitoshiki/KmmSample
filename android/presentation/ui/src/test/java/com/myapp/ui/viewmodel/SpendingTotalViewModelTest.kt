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
 * Spending管理画面　UIロジック使用
 *
 */
class SpendingTotalViewModelTest {

    private var state = SpendingContract.State()

    private lateinit var viewModel: SpendingTotalViewModel
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
            coEvery { useCase.getSpendingCoin() } returns MockUtil.getSpendingCoin()
            MockUtil.getAllAsset().values().map {
                coEvery { useCase.changeStableRate(it, any()) } returns it.value * 5
            }
        }
    }
    private fun initViewModel() {
        viewModel = SpendingTotalViewModel(coinUseCase)
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
        state: SpendingContract.State,
        effect: SpendingContract.Effect? = null
    ) {
        val resultState = viewModel.state.value
        var resultEffect: SpendingContract.Effect? = null
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
     * 期待結果：チャート値に取得したSpendingの値が設定されること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun init() {
        // 期待結果
        val chartValues = coinUseCase.getSpendingCoin().values().map {
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