package com.myapp.ui.viewmodel

import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.model.value.*
import com.myapp.presentation.extension.changeStrValue
import com.myapp.test.MockUtil
import io.mockk.coEvery
import io.mockk.coVerify
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
 * 設定画面　UIロジック仕様
 *
 */
class SettingViewModelTest {

    private var state = SettingContract.State()

    private lateinit var viewModel: SettingViewModel
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
            coEvery { useCase.getSpendingCoin() } returns MockUtil.getSpendingCoin()
            coEvery { useCase.getRateCoin(any()) } returns MockUtil.getRateCoin()
            coEvery { useCase.updateSpendingAssets(any()) } returns Unit
            coEvery { useCase.updateRateAssets(any()) } returns Unit
            coEvery { useCase.updateWalletAssets(any()) } returns Unit

        }
    }
    private fun initViewModel() {
        viewModel = SettingViewModel(coinUseCase)
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
        state: SettingContract.State,
        effect: SettingContract.Effect? = null
    ) {
        val resultState = viewModel.state.value
        var resultEffect: SettingContract.Effect? = null
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
        val wallet = coinUseCase.getWalletCoin()
        val spending = coinUseCase.getSpendingCoin()
        val rate = coinUseCase.getRateCoin(LegalTender.JPY)
        val resultState = state.copy(
            spendingGmt = spending.gmt.value.changeStrValue(),
            spendingGst = spending.gst.value.changeStrValue(),
            spendingSol = spending.sol.value.changeStrValue(),
            spendingGem = spending.gem.value.changeStrValue(),
            spendingShoebox = spending.shoebox.value.changeStrValue(),
            spendingSneaker = spending.sneaker.value.changeStrValue(),
            walletGmt = wallet.gmt.value.changeStrValue(),
            walletGst = wallet.gst.value.changeStrValue(),
            walletSol = wallet.sol.value.changeStrValue(),
            walletUsdc = wallet.usdc.value.changeStrValue(),
            walletGem = wallet.gem.value.changeStrValue(),
            walletShoebox = wallet.shoebox.value.changeStrValue(),
            walletSneaker = wallet.sneaker.value.changeStrValue(),
            rateGmt = rate.gmt.value.changeStrValue(),
            rateGst = rate.gst.value.changeStrValue(),
            rateSol = rate.sol.value.changeStrValue(),
            rateUsdc = rate.usdc.value.changeStrValue(),
            rateGem = rate.gem.value.changeStrValue(),
            rateShoebox = rate.shoebox.value.changeStrValue(),
            rateSneaker = rate.sneaker.value.changeStrValue(),
            beforeSpendingGmt = spending.gmt.value.changeStrValue(),
            beforeSpendingGst = spending.gst.value.changeStrValue(),
            beforeSpendingSol = spending.sol.value.changeStrValue(),
            beforeSpendingGem = spending.gem.value.changeStrValue(),
            beforeSpendingShoebox = spending.shoebox.value.changeStrValue(),
            beforeSpendingSneaker = spending.sneaker.value.changeStrValue(),
            beforeWalletGmt = wallet.gmt.value.changeStrValue(),
            beforeWalletGst = wallet.gst.value.changeStrValue(),
            beforeWalletSol = wallet.sol.value.changeStrValue(),
            beforeWalletUsdc = wallet.usdc.value.changeStrValue(),
            beforeWalletGem = wallet.gem.value.changeStrValue(),
            beforeWalletShoebox = wallet.shoebox.value.changeStrValue(),
            beforeWalletSneaker = wallet.sneaker.value.changeStrValue(),
            beforeRateGmt = rate.gmt.value.changeStrValue(),
            beforeRateGst = rate.gst.value.changeStrValue(),
            beforeRateSol = rate.sol.value.changeStrValue(),
            beforeRateUsdc = rate.usdc.value.changeStrValue(),
            beforeRateGem = rate.gem.value.changeStrValue(),
            beforeRateShoebox = rate.shoebox.value.changeStrValue(),
            beforeRateSneaker = rate.sneaker.value.changeStrValue()
        )

        // 実施

        // 比較
        result(resultState)
    }

    // endregion

    // region 入力制御

    /**
    * レートのGMT値変更
    *
    * 条件：様々な文字入力
    * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
    *
    */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateGmt() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateGmt = it.value,
                        enabledRateGmt = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateGmt = it.value,
                        enabledRateGmt = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateGmt(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * レートのGST値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateGst() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateGst = it.value,
                        enabledRateGst = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateGst = it.value,
                        enabledRateGst = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateGst(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * レートのSOL値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateSol() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateSol = it.value,
                        enabledRateSol = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateSol = it.value,
                        enabledRateSol = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateSol(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * レートのUSDC値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateUsdc() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateUsdc = it.value,
                        enabledRateUsdc = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateUsdc = it.value,
                        enabledRateUsdc = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateUsdc(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * レートのGem値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateGem() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateGem = it.value,
                        enabledRateGem = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateGem = it.value,
                        enabledRateGem = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateGem(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * レートのShoebox値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateShoebox() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateShoebox = it.value,
                        enabledRateShoebox = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateShoebox = it.value,
                        enabledRateShoebox = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateShoebox(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * レートのSneaker値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeRateSneaker() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        rateSneaker = it.value,
                        enabledRateSneaker = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        rateSneaker = it.value,
                        enabledRateSneaker = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeRateSneaker(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのGMT値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletGmt() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        walletGmt = it.value,
                        enabledWalletGmt = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletGmt = it.value,
                        enabledWalletGmt = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletGmt(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのGST値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletGst() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        walletGst = it.value,
                        enabledWalletGst = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletGst = it.value,
                        enabledWalletGst = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletGst(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのSOL値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletSol() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        walletSol = it.value,
                        enabledWalletSol = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletSol = it.value,
                        enabledWalletSol = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletSol(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのUSDC値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletUsdc() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        walletUsdc = it.value,
                        enabledWalletUsdc = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletUsdc = it.value,
                        enabledWalletUsdc = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletUsdc(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのGem値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletGem() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY -> {
                    state.copy(
                        walletGem = it.value,
                        enabledWalletGem = false
                    )
                }
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletGem = it.value,
                        enabledWalletGem = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletGem(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのShoebox値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletShoebox() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY -> {
                    state.copy(
                        walletShoebox = it.value,
                        enabledWalletShoebox = false
                    )
                }
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletShoebox = it.value,
                        enabledWalletShoebox = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletShoebox(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * WalletのSneaker値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeWalletSneaker() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY -> {
                    state.copy(
                        walletSneaker = it.value,
                        enabledWalletSneaker = false
                    )
                }
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        walletSneaker = it.value,
                        enabledWalletSneaker = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeWalletSneaker(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * SpendingのGMT値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeSpendingGmt() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        spendingGmt = it.value,
                        enabledSpendingGmt = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        spendingGmt = it.value,
                        enabledSpendingGmt = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeSpendingGmt(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * SpendingのGST値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeSpendingGst() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        spendingGst = it.value,
                        enabledSpendingGst = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        spendingGst = it.value,
                        enabledSpendingGst = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeSpendingGst(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * SpendingのSOL値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeSpendingSol() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY,
                MockUtil.Characters.PERIOD -> {
                    state.copy(
                        spendingSol = it.value,
                        enabledSpendingSol = false
                    )
                }
                MockUtil.Characters.NUMBER_FLOAT,
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        spendingSol = it.value,
                        enabledSpendingSol = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeSpendingSol(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * SpendingのGem値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeSpendingGem() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY -> {
                    state.copy(
                        spendingGem = it.value,
                        enabledSpendingGem = false
                    )
                }
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        spendingGem = it.value,
                        enabledSpendingGem = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeSpendingGem(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * SpendingのShoebox値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeSpendingShoebox() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY -> {
                    state.copy(
                        spendingShoebox = it.value,
                        enabledSpendingShoebox = false
                    )
                }
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        spendingShoebox = it.value,
                        enabledSpendingShoebox = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeSpendingShoebox(it.value))
            // 比較
            result(resultState)
        }
    }

    /**
     * SpendingのSneaker値変更
     *
     * 条件：様々な文字入力
     * 期待結果：
     * ・数値、ピリオド(.)以外は入力が拒否されること
     * ・数値以外と初期値の場合は更新ボタンが活性状態にならないこと
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onChangeSpendingSneaker() {
        MockUtil.Characters.values().forEach {
            initViewModel()
            // 期待結果
            val resultState = when (it) {
                MockUtil.Characters.EMPTY -> {
                    state.copy(
                        spendingSneaker = it.value,
                        enabledSpendingSneaker = false
                    )
                }
                MockUtil.Characters.NUMBER_HALF -> {
                    state.copy(
                        spendingSneaker = it.value,
                        enabledSpendingSneaker = true
                    )
                }
                else -> {
                    state.copy()
                }
            }
            // 実施
            viewModel.setEvent(SettingContract.Event.OnChangeSpendingSneaker(it.value))
            // 比較
            result(resultState)
        }
    }
    // endregion

    // region 更新処理

    /**
     * SpendingのGST値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateSpendingGst() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeSpendingGst = input,
            spendingGst = input,
            enabledSpendingGst = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeSpendingGst(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.GST))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateSpendingAssets(GstCoin(input.toFloat())) }
    }

    /**
     * SpendingのGMT値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateSpendingGmt() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeSpendingGmt = input,
            spendingGmt = input,
            enabledSpendingGmt = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeSpendingGmt(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.GMT))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateSpendingAssets(GmtCoin(input.toFloat())) }
    }

    /**
     * SpendingのSOL値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateSpendingSol() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeSpendingSol = input,
            spendingSol = input,
            enabledSpendingSol = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeSpendingSol(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateSpendingAssets(StepnCoinType.SOL))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateSpendingAssets(SolanaCoin(input.toFloat())) }
    }

    /**
     * SpendingのGem値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateSpendingGem() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeSpendingGem = input,
            spendingGem = input,
            enabledSpendingGem = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeSpendingGem(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.GEM))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateSpendingAssets(GemAssets(input.toFloat())) }
    }

    /**
     * SpendingのShoebox値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateSpendingShoebox() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeSpendingShoebox = input,
            spendingShoebox = input,
            enabledSpendingShoebox = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeSpendingShoebox(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.SHOEBOX))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateSpendingAssets(ShoeboxAssets(input.toFloat())) }
    }

    /**
     * SpendingのSneaker値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateSpendingSneaker() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeSpendingSneaker = input,
            spendingSneaker = input,
            enabledSpendingSneaker = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeSpendingSneaker(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateSpendingAssets(RealAssetsType.SNEAKER))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateSpendingAssets(SneakerAssets(input.toFloat())) }
    }

    /**
     * WalletのGST値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletGst() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletGst = input,
            walletGst = input,
            enabledWalletGst = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletGst(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.GST))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(GstCoin(input.toFloat())) }
    }

    /**
     * WalletのGMT値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletGmt() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletGmt = input,
            walletGmt = input,
            enabledWalletGmt = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletGmt(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.GMT))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(GmtCoin(input.toFloat())) }
    }

    /**
     * WalletのSOL値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletSol() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletSol = input,
            walletSol = input,
            enabledWalletSol = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletSol(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.SOL))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(SolanaCoin(input.toFloat())) }
    }

    /**
     * WalletのUsdc値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletUsdc() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletUsdc = input,
            walletUsdc = input,
            enabledWalletUsdc = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletUsdc(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(StepnCoinType.USCD))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(UsdcCoin(input.toFloat())) }
    }

    /**
     * WalletのGem値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletGem() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletGem = input,
            walletGem = input,
            enabledWalletGem = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletGem(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.GEM))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(GemAssets(input.toFloat())) }
    }

    /**
     * WalletのShoebox値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletShoebox() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletShoebox = input,
            walletShoebox = input,
            enabledWalletShoebox = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletShoebox(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.SHOEBOX))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(ShoeboxAssets(input.toFloat())) }
    }

    /**
     * WalletのSneaker値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateWalletSneaker() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeWalletSneaker = input,
            walletSneaker = input,
            enabledWalletSneaker = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeWalletSneaker(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateWalletAssets(RealAssetsType.SNEAKER))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateWalletAssets(SneakerAssets(input.toFloat())) }
    }

    /**
     * RateのGST値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateGst() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateGst = input,
            rateGst = input,
            enabledRateGst = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateGst(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.GST))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(GstCoin(input.toFloat())) }
    }

    /**
     * RateのGMT値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateGmt() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateGmt = input,
            rateGmt = input,
            enabledRateGmt = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateGmt(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.GMT))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(GmtCoin(input.toFloat())) }
    }

    /**
     * RateのSOL値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateSol() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateSol = input,
            rateSol = input,
            enabledRateSol = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateSol(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.SOL))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(SolanaCoin(input.toFloat())) }
    }

    /**
     * RateのUsdc値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateUsdc() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateUsdc = input,
            rateUsdc = input,
            enabledRateUsdc = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateUsdc(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(StepnCoinType.USCD))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(UsdcCoin(input.toFloat())) }
    }

    /**
     * RateのGem値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateGem() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateGem = input,
            rateGem = input,
            enabledRateGem = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateGem(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.GEM))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(GemAssets(input.toFloat())) }
    }

    /**
     * RateのShoebox値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateShoebox() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateShoebox = input,
            rateShoebox = input,
            enabledRateShoebox = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateShoebox(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.SHOEBOX))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(ShoeboxAssets(input.toFloat())) }
    }

    /**
     * RateのSneaker値更新
     *
     * 条件：なし
     * 期待結果：
     * ・入力した値で更新処理が呼ばれること
     * ・更新制御値が非活性になること
     * ・入力前の値が更新した値になること
     *
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onUpdateRateSneaker() {
        // 期待結果
        val input = MockUtil.Characters.NUMBER_HALF.value
        val resultState = state.copy(
            beforeRateSneaker = input,
            rateSneaker = input,
            enabledRateSneaker = false
        )

        // 実施
        viewModel.setEvent(SettingContract.Event.OnChangeRateSneaker(input))
        viewModel.setEvent(SettingContract.Event.OnUpdateRateAssets(RealAssetsType.SNEAKER))

        // 比較
        result(resultState)
        coVerify(exactly = 1) { (coinUseCase).updateRateAssets(SneakerAssets(input.toFloat())) }
    }

    // endregion
}