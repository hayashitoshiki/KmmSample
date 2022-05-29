package com.myapp.kmmsample.usecase

import com.myapp.kmmsample.repository.LocalCoinRepository
import com.myapp.model.entity.AllAsset
import com.myapp.model.value.*
import com.myapp.test.MockUtil
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * コイン関連のロジック仕様
 *
 */
class CoinUseCaseImplTest {

    private lateinit var coinUseCase: CoinUseCase
    private lateinit var localCoinRepository: LocalCoinRepository
    @Before
    fun setUp() {
        coinRepositoryAllTrue()
        initViewModel()
    }

    private fun coinRepositoryAllTrue() {
        localCoinRepository = mockk<LocalCoinRepository>().also { useCase ->
            coEvery { useCase.getWalletCoin() } returns MockUtil.getWalletCoin()
            coEvery { useCase.getSpendingCoin() } returns MockUtil.getSpendingCoin()
            coEvery { useCase.getRateCoin() } returns MockUtil.getRateCoin()
            coEvery { useCase.updateWalletGmt(any()) } returns Unit
            coEvery { useCase.updateWalletGst(any()) } returns Unit
            coEvery { useCase.updateWalletSol(any()) } returns Unit
            coEvery { useCase.updateWalletUsdc(any()) } returns Unit
            coEvery { useCase.updateWalletGem(any()) } returns Unit
            coEvery { useCase.updateWalletShoebox(any()) } returns Unit
            coEvery { useCase.updateWalletSneaker(any()) } returns Unit
            coEvery { useCase.updateRateGmt(any()) } returns Unit
            coEvery { useCase.updateRateGst(any()) } returns Unit
            coEvery { useCase.updateRateSol(any()) } returns Unit
            coEvery { useCase.updateRateUsdc(any()) } returns Unit
            coEvery { useCase.updateRateGem(any()) } returns Unit
            coEvery { useCase.updateRateShoebox(any()) } returns Unit
            coEvery { useCase.updateRateSneaker(any()) } returns Unit
            coEvery { useCase.updateSpendingGmt(any()) } returns Unit
            coEvery { useCase.updateSpendingGst(any()) } returns Unit
            coEvery { useCase.updateSpendingSol(any()) } returns Unit
            coEvery { useCase.updateSpendingGem(any()) } returns Unit
            coEvery { useCase.updateSpendingShoebox(any()) } returns Unit
            coEvery { useCase.updateSpendingSneaker(any()) } returns Unit
        }
    }
    private fun initViewModel() {
        coinUseCase = CoinUseCaseImpl(localCoinRepository)
    }

    @After
    fun tearDown() {
    }

    /**
     * 全財産取得
     *
     * 条件：なし
     * 期待結果：SpendingとWalletの合計値が返却されること
     */
    @Test
    fun getAllAsset() {
        val spending = MockUtil.getSpendingCoin()
        val wallet = MockUtil.getWalletCoin()
        val expected =  AllAsset(
            GstCoin(spending.gst.value + wallet.gst.value),
            GmtCoin(spending.gmt.value + wallet.gmt.value),
            SolanaCoin(spending.sol.value + wallet.sol.value),
            wallet.usdc,
            GemAssets((spending.gem.value + wallet.gem.value)),
            ShoeboxAssets((spending.shoebox.value + wallet.shoebox.value)),
            SneakerAssets((spending.sneaker.value + wallet.sneaker.value))
        )
        val result = coinUseCase.getAllAsset()
        assertEquals(expected, result)
    }

    /**
     * Wallet内資産取得
     *
     * 条件：なし
     * 期待結果：Wallet内資産が返却されること
     */
    @Test
    fun getWalletCoin() {
        val result = coinUseCase.getWalletCoin()
        assertEquals(MockUtil.getWalletCoin(), result)
    }

    /**
     * Spending内資産取得
     *
     * 条件：なし
     * 期待結果：Spending内資産が返却されること
     */
    @Test
    fun getSpendingCoin() {
        val result = coinUseCase.getSpendingCoin()
        assertEquals(MockUtil.getSpendingCoin(), result)

    }

    /**
     * レート取得
     *
     * 条件：なし
     * 期待結果：各種レートが返却されること
     */
    @Test
    fun getRateCoin() {
        val result = coinUseCase.getRateCoin(LegalTender.JPY)
        assertEquals(MockUtil.getRateCoin(), result)
    }

    /**
     * Wallet内資産更新
     *
     * 条件：なし
     * 期待結果：指定したWallet資産の更新処理が呼ばれること
     */
    @Test
    fun updateWalletAssets() {
        MockUtil.assetsLists.forEach { assets ->
            coinUseCase.updateWalletAssets(assets)
            coVerify(exactly = 1) {
                when(assets) {
                    is GmtCoin -> localCoinRepository.updateWalletGmt(assets)
                    is GstCoin -> localCoinRepository.updateWalletGst(assets)
                    is SolanaCoin -> localCoinRepository.updateWalletSol(assets)
                    is UsdcCoin -> localCoinRepository.updateWalletUsdc(assets)
                    is GemAssets -> localCoinRepository.updateWalletGem(assets)
                    is ShoeboxAssets -> localCoinRepository.updateWalletShoebox(assets)
                    is SneakerAssets -> localCoinRepository.updateWalletSneaker(assets)
                }
            }
        }
    }

    /**
     * Spending内資産更新
     *
     * 条件：なし
     * 期待結果：指定したSpending資産の更新処理が呼ばれること
     */
    @Test
    fun updateSpendingAssets() {
        MockUtil.assetsLists.forEach { assets ->
            runCatching { coinUseCase.updateSpendingAssets(assets) }
                .onSuccess { if (assets is UsdcCoin) fail() }
                .onFailure { }
            if (assets !is UsdcCoin) {
                coVerify(exactly = 1) {
                    when (assets) {
                        is GmtCoin -> localCoinRepository.updateSpendingGmt(assets)
                        is GstCoin -> localCoinRepository.updateSpendingGst(assets)
                        is SolanaCoin -> localCoinRepository.updateSpendingSol(assets)
                        is GemAssets -> localCoinRepository.updateSpendingGem(assets)
                        is ShoeboxAssets -> localCoinRepository.updateSpendingShoebox(assets)
                        is SneakerAssets -> localCoinRepository.updateSpendingSneaker(assets)
                    }
                }
            }
        }
    }

    /**
     * レート更新
     *
     * 条件：なし
     * 期待結果：指定したレートの更新処理が呼ばれること
     */
    @Test
    fun updateRateAssets() {
        MockUtil.assetsLists.forEach { assets ->
            coinUseCase.updateRateAssets(assets)
            coVerify(exactly = 1) {
                when(assets) {
                    is GmtCoin -> localCoinRepository.updateRateGmt(assets)
                    is GstCoin -> localCoinRepository.updateRateGst(assets)
                    is SolanaCoin -> localCoinRepository.updateRateSol(assets)
                    is UsdcCoin -> localCoinRepository.updateRateUsdc(assets)
                    is GemAssets -> localCoinRepository.updateRateGem(assets)
                    is ShoeboxAssets -> localCoinRepository.updateRateShoebox(assets)
                    is SneakerAssets -> localCoinRepository.updateRateSneaker(assets)
                }
            }
        }
    }

    /**
     * 法定通貨計算結果取得
     *
     * 条件：なし
     * 期待結果：各種法定通貨計算した結果が返却されること
     */
    @Test
    fun changeStableRate() {
        val rate = MockUtil.getRateCoin()
        MockUtil.assetsLists.forEach { assets ->
            val result = coinUseCase.changeStableRate(assets, LegalTender.JPY)
            val expected = when(assets) {
                is GmtCoin -> assets.value * rate.getRate(assets.type()).value
                is GstCoin -> assets.value * rate.getRate(assets.type()).value
                is SolanaCoin -> assets.value * rate.getRate(assets.type()).value
                is UsdcCoin -> assets.value * rate.getRate(assets.type()).value
                is GemAssets -> assets.value * rate.getRate(assets.type()).value * rate.getRate(StepnCoinType.SOL).value
                is ShoeboxAssets -> assets.value * rate.getRate(assets.type()).value * rate.getRate(StepnCoinType.SOL).value
                is SneakerAssets -> assets.value * rate.getRate(assets.type()).value * rate.getRate(StepnCoinType.SOL).value
            }
            assertEquals(expected, result)
        }
    }

}