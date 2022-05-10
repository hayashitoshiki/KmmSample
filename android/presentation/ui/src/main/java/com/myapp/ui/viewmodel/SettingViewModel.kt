package com.myapp.presentation.viewmodel

import com.myapp.composesample.util.base.BaseContract
import com.myapp.composesample.util.base.BaseViewModel
import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.model.value.*
import com.myapp.presentation.extension.changeStrValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 設定画面のUIロジック
 *
 */
@HiltViewModel
class SettingViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase
) : BaseViewModel<SettingContract.State, SettingContract.Effect, SettingContract.Event>() {

    init {
        val wallet = coinUseCase.getWalletCoin()
        val spending = coinUseCase.getSpendingCoin()
        val rate = coinUseCase.getRateCoin()

        setState {
            copy(
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
                beforeRateSneaker = rate.sneaker.value.changeStrValue(),
            )
        }
    }

    override fun initState(): SettingContract.State {
        return SettingContract.State()
    }

    override fun handleEvents(event: SettingContract.Event) = when(event) {
        is SettingContract.Event.OnUpdateRateAssets -> onUpdateRateCoin(event.type)
        is SettingContract.Event.OnUpdateWalletAssets -> onUpdateWalletCoin(event.type)
        is SettingContract.Event.OnUpdateSpendingAssets -> onUpdateSpendingCoin(event.type)
        is SettingContract.Event.OnChangeRateGmt -> onChangeRateGmt(event.coin)
        is SettingContract.Event.OnChangeRateGst -> onChangeRateGst(event.coin)
        is SettingContract.Event.OnChangeRateSol -> onChangeRateSol(event.coin)
        is SettingContract.Event.OnChangeRateUsdc -> onChangeRateUsdc(event.coin)
        is SettingContract.Event.OnChangeSpendingGmt -> onChangeSpendingGmt(event.coin)
        is SettingContract.Event.OnChangeSpendingGst -> onChangeSpendingGst(event.coin)
        is SettingContract.Event.OnChangeSpendingSol -> onChangeSpendingSol(event.coin)
        is SettingContract.Event.OnChangeWalletGmt -> onChangeWalletGmt(event.coin)
        is SettingContract.Event.OnChangeWalletGst -> onChangeWalletGst(event.coin)
        is SettingContract.Event.OnChangeWalletSol -> onChangeWalletSol(event.coin)
        is SettingContract.Event.OnChangeWalletUsdc -> onChangeWalletUsdc(event.coin)
        is SettingContract.Event.OnChangeRateGem -> onChangeRateGem(event.sol)
        is SettingContract.Event.OnChangeRateShoebox -> onChangeRateShoebox(event.sol)
        is SettingContract.Event.OnChangeRateSneaker -> onChangeRateSneaker(event.sol)
        is SettingContract.Event.OnChangeSpendingGem -> onChangeSpendingGem(event.count)
        is SettingContract.Event.OnChangeSpendingShoebox -> onChangeSpendingShoebox(event.count)
        is SettingContract.Event.OnChangeSpendingSneaker -> onChangeSpendingSneaker(event.count)
        is SettingContract.Event.OnChangeWalletGem -> onChangeWalletGem(event.count)
        is SettingContract.Event.OnChangeWalletShoebox -> onChangeWalletShoebox(event.count)
        is SettingContract.Event.OnChangeWalletSneaker -> onChangeWalletSneaker(event.count)
    }

    private fun changeCoinValue(value: String) : Float {
        return if (value.endsWith(".")) {
            (value + "0").toFloat()
        } else {
            value.toFloat()
        }
    }
    private fun checkCoinValueUpdate(value: String) : Boolean {
        val regexNumber = "[0-9.]{1,8}".toRegex()

        if (value.isEmpty()) return true
        if (value.startsWith(".")) return false
        if (!regexNumber.matches(value)) return false
        value.chunked(1)
            .filter { it == "." }
            .size
            .also{ if(it >= 2) return false }
        return true
    }

    private fun checkCountValueUpdate(value: String) : Boolean {
        val regexNumber = "[0-9]{1,8}".toRegex()

        if (value.isEmpty()) return true
        if (value.startsWith(".")) return false
        if (!regexNumber.matches(value)) return false
        return true
    }


    private fun onUpdateSpendingCoin(type: AssetsType) {
        val assets = when(type) {
            StepnCoinType.GMT -> {
                setState { copy(beforeSpendingGmt = state.value.spendingGmt, enabledSpendingGmt = false) }
                GmtCoin(changeCoinValue(state.value.spendingGmt))
            }
            StepnCoinType.GST -> {
                setState { copy(beforeSpendingGst = state.value.spendingGst, enabledSpendingGst = false) }
               GstCoin(changeCoinValue(state.value.spendingGst))
            }
            StepnCoinType.SOL -> {
                setState { copy(beforeSpendingSol = state.value.spendingSol, enabledSpendingSol = false) }
                SolanaCoin(changeCoinValue(state.value.spendingSol))
            }
            StepnCoinType.USCD -> {
               throw IllegalAccessError("SpendingはUSDCには存在しません。")
            }
            RealAssetsType.GEM -> {
                setState { copy(beforeSpendingGem = state.value.spendingGem, enabledSpendingGem = false) }
                GemAssets(changeCoinValue(state.value.spendingGem))
            }
            RealAssetsType.SHOEBOX -> {
                setState { copy(beforeSpendingShoebox = state.value.spendingShoebox, enabledSpendingShoebox = false) }
                ShoeboxAssets(changeCoinValue(state.value.spendingShoebox))
            }
            RealAssetsType.SNEAKER -> {
                setState { copy(beforeSpendingSneaker = state.value.spendingSneaker, enabledSpendingSneaker = false) }
                SneakerAssets(changeCoinValue(state.value.spendingSneaker))
            }
        }
        coinUseCase.updateSpendingAssets(assets)
    }
    private fun onUpdateRateCoin(type: AssetsType) {
        val assets = when(type) {
            StepnCoinType.GMT -> {
                setState { copy(beforeRateGmt = state.value.rateGmt, enabledRateGmt = false) }
                GmtCoin(changeCoinValue(state.value.rateGmt))
            }
            StepnCoinType.GST -> {
                setState { copy(beforeRateGst = state.value.rateGst, enabledRateGst = false) }
                GstCoin(changeCoinValue(state.value.rateGst))
            }
            StepnCoinType.SOL -> {
                setState { copy(beforeRateSol = state.value.rateSol, enabledRateSol = false) }
                SolanaCoin(changeCoinValue(state.value.rateSol))
            }
            StepnCoinType.USCD -> {
                setState { copy(beforeRateUsdc = state.value.rateUsdc, enabledRateUsdc = false) }
                UsdcCoin(changeCoinValue(state.value.rateUsdc))
            }
            RealAssetsType.GEM -> {
                setState { copy(beforeRateGem = state.value.rateGem, enabledRateGem = false) }
                GemAssets(changeCoinValue(state.value.rateGem))
            }
            RealAssetsType.SHOEBOX -> {
                setState { copy(beforeRateShoebox = state.value.rateShoebox, enabledRateShoebox = false) }
                ShoeboxAssets(changeCoinValue(state.value.rateShoebox))
            }
            RealAssetsType.SNEAKER -> {
                setState { copy(beforeRateSneaker = state.value.rateSneaker, enabledRateSneaker = false) }
                SneakerAssets(changeCoinValue(state.value.rateSneaker))
            }
        }
        coinUseCase.updateRateAssets(assets)
    }
    private fun onUpdateWalletCoin(type: AssetsType) {
        val assets = when(type) {
            StepnCoinType.GMT -> {
                setState { copy(beforeWalletGmt = state.value.walletGmt, enabledWalletGmt = false) }
                GmtCoin(changeCoinValue(state.value.walletGmt))
            }
            StepnCoinType.GST -> {
                setState { copy(beforeWalletGst = state.value.walletGst, enabledWalletGst = false) }
                GstCoin(changeCoinValue(state.value.walletGst))
            }
            StepnCoinType.SOL -> {
                setState { copy(beforeWalletSol = state.value.walletSol, enabledWalletSol = false) }
                SolanaCoin(changeCoinValue(state.value.walletSol))
            }
            StepnCoinType.USCD -> {
                setState { copy(beforeWalletUsdc = state.value.walletUsdc, enabledWalletUsdc = false) }
                UsdcCoin(changeCoinValue(state.value.walletUsdc))
            }
            RealAssetsType.GEM -> {
                setState { copy(beforeWalletGem = state.value.walletGem, enabledWalletGem = false) }
                GemAssets(changeCoinValue(state.value.walletGem))
            }
            RealAssetsType.SHOEBOX -> {
                setState { copy(beforeWalletShoebox = state.value.walletShoebox, enabledWalletShoebox = false) }
                ShoeboxAssets(changeCoinValue(state.value.walletShoebox))
            }
            RealAssetsType.SNEAKER -> {
                setState { copy(beforeWalletSneaker = state.value.walletSneaker, enabledWalletSneaker = false) }
                SneakerAssets(changeCoinValue(state.value.walletSneaker))
            }
        }
        coinUseCase.updateWalletAssets(assets)
    }
    private fun onChangeSpendingGst(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeSpendingGst != coin && coin.isNotEmpty()
        setState { copy(spendingGst = coin, enabledSpendingGst = enabled) }
    }
    private fun onChangeSpendingSol(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeSpendingSol != coin && coin.isNotEmpty()
        setState { copy(spendingSol = coin, enabledSpendingSol = enabled) }
    }
    private fun onChangeSpendingGmt(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeSpendingGmt != coin && coin.isNotEmpty()
        setState { copy(spendingGmt = coin, enabledSpendingGmt = enabled) }
    }
    private fun onChangeSpendingGem(assets: String) {
        if (!checkCountValueUpdate(assets)) return
        val enabled = state.value.beforeSpendingGem != assets && assets.isNotEmpty()
        setState { copy(spendingGem = assets, enabledSpendingGem = enabled) }
    }
    private fun onChangeSpendingShoebox(assets: String) {
        if (!checkCountValueUpdate(assets)) return
        val enabled = state.value.beforeSpendingShoebox != assets && assets.isNotEmpty()
        setState { copy(spendingShoebox = assets, enabledSpendingShoebox = enabled) }
    }
    private fun onChangeSpendingSneaker(assets: String) {
        if (!checkCountValueUpdate(assets)) return
        val enabled = state.value.beforeSpendingSneaker != assets && assets.isNotEmpty()
        setState { copy(spendingSneaker = assets, enabledSpendingSneaker = enabled) }
    }
    private fun onChangeRateGmt(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeRateGmt != coin && coin.isNotEmpty()
        setState { copy(rateGmt = coin, enabledRateGmt = enabled) }
    }
    private fun onChangeRateGst(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeRateGst != coin && coin.isNotEmpty()
        setState { copy(rateGst = coin, enabledRateGst = enabled) }
    }
    private fun onChangeRateSol(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeRateSol != coin && coin.isNotEmpty()
        setState { copy(rateSol = coin, enabledRateSol = enabled) }
    }
    private fun onChangeRateUsdc(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeRateUsdc != coin && coin.isNotEmpty()
        setState { copy(rateUsdc = coin, enabledRateSol = enabled) }
    }
    private fun onChangeRateGem(assets: String) {
        if (!checkCoinValueUpdate(assets)) return
        val enabled = state.value.beforeRateGem != assets && assets.isNotEmpty()
        setState { copy(rateGem = assets, enabledRateGem = enabled) }
    }
    private fun onChangeRateShoebox(assets: String) {
        if (!checkCoinValueUpdate(assets)) return
        val enabled = state.value.beforeRateShoebox != assets && assets.isNotEmpty()
        setState { copy(rateShoebox = assets, enabledRateShoebox = enabled) }
    }
    private fun onChangeRateSneaker(assets: String) {
        if (!checkCoinValueUpdate(assets)) return
        val enabled = state.value.beforeRateSneaker != assets && assets.isNotEmpty()
        setState { copy(rateSneaker = assets, enabledRateSneaker = enabled) }
    }
    private fun onChangeWalletGmt(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeWalletGmt != coin && coin.isNotEmpty()
        setState { copy(walletGmt = coin, enabledWalletGmt = enabled) }
    }
    private fun onChangeWalletGst(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeWalletGst != coin && coin.isNotEmpty()
        setState { copy(walletGst = coin, enabledWalletGst = enabled) }
    }
    private fun onChangeWalletSol(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeWalletSol != coin && coin.isNotEmpty()
        setState { copy(walletSol = coin, enabledWalletSol = enabled) }
    }
    private fun onChangeWalletUsdc(coin: String) {
        if (!checkCoinValueUpdate(coin)) return
        val enabled = state.value.beforeWalletUsdc != coin && coin.isNotEmpty()
        setState { copy(walletUsdc = coin, enabledWalletUsdc = enabled) }
    }
    private fun onChangeWalletGem(assets: String) {
        if (!checkCountValueUpdate(assets)) return
        val enabled = state.value.beforeWalletGem != assets && assets.isNotEmpty()
        setState { copy(walletGem = assets, enabledWalletGem = enabled) }
    }
    private fun onChangeWalletShoebox(assets: String) {
        if (!checkCountValueUpdate(assets)) return
        val enabled = state.value.beforeWalletShoebox != assets && assets.isNotEmpty()
        setState { copy(walletShoebox = assets, enabledWalletShoebox = enabled) }
    }
    private fun onChangeWalletSneaker(assets: String) {
        if (!checkCountValueUpdate(assets)) return
        val enabled = state.value.beforeWalletSneaker != assets && assets.isNotEmpty()
        setState { copy(walletSneaker = assets, enabledWalletSneaker = enabled) }
    }
}


/**
 * チャートサンプル画面 UI状態管理
 *
 */
interface SettingContract : BaseContract {

    /**
     * 状態保持
     *
     * @property spendingGmt SpendingのGMTコイン
     * @property spendingGst SpendingのGSTコイン
     * @property spendingSol SpendingのSolanaコイン
     * @property spendingGem SpendingのGem保持数
     * @property spendingShoebox SpendingのShoebox保持数
     * @property spendingSneaker SpendingのSneaker保持数
     * @property walletGmt WalletのGSTコイン
     * @property walletGst WalletのGSTコイン
     * @property walletSol WalletのSolanaコイン
     * @property walletUsdc WalletのUSDコイン
     * @property walletGem WalletのGem保持数
     * @property walletShoebox WalletのShoebox保持数
     * @property walletSneaker WalletのSneaker保持数
     * @property rateGmt GMTコインの日本円レート
     * @property rateGst GSTコインの日本円レート
     * @property rateSol Solanaコインの日本円レート
     * @property rateUsdc USDコインの日本円レート
     * @property rateGem GemのSolanaレート
     * @property rateShoebox ShoeboxのSolanaレート
     * @property rateSneaker WalletのSolanaレート
     */
    data class State(
        val spendingGmt: String = "0",
        val spendingGst: String = "0",
        val spendingSol: String = "0",
        val spendingGem: String = "0",
        val spendingShoebox: String = "0",
        val spendingSneaker: String = "0",
        val walletGmt: String = "0",
        val walletGst: String = "0",
        val walletSol: String = "0",
        val walletUsdc: String = "0",
        val walletGem: String = "0",
        val walletShoebox: String = "0",
        val walletSneaker: String = "0",
        val rateGmt: String = "0",
        val rateGst: String = "0",
        val rateSol: String = "0",
        val rateUsdc: String = "0",
        val rateGem: String = "0",
        val rateShoebox: String = "0",
        val rateSneaker: String = "0",
        val beforeSpendingGmt: String = "0",
        val beforeSpendingGst: String = "0",
        val beforeSpendingSol: String = "0",
        val beforeSpendingGem: String = "0",
        val beforeSpendingShoebox: String = "0",
        val beforeSpendingSneaker: String = "0",
        val beforeWalletGmt: String = "0",
        val beforeWalletGst: String = "0",
        val beforeWalletSol: String = "0",
        val beforeWalletUsdc: String = "0",
        val beforeWalletGem: String = "0",
        val beforeWalletShoebox: String = "0",
        val beforeWalletSneaker: String = "0",
        val beforeRateGmt: String = "0",
        val beforeRateGst: String = "0",
        val beforeRateSol: String = "0",
        val beforeRateUsdc: String = "0",
        val beforeRateGem: String = "0",
        val beforeRateShoebox: String = "0",
        val beforeRateSneaker: String = "0",
        val enabledSpendingGmt: Boolean = false,
        val enabledSpendingGst: Boolean = false,
        val enabledSpendingSol: Boolean = false,
        val enabledSpendingGem: Boolean = false,
        val enabledSpendingShoebox: Boolean = false,
        val enabledSpendingSneaker: Boolean = false,
        val enabledWalletGmt: Boolean = false,
        val enabledWalletGst: Boolean = false,
        val enabledWalletSol: Boolean = false,
        val enabledWalletUsdc: Boolean = false,
        val enabledWalletGem: Boolean = false,
        val enabledWalletShoebox: Boolean = false,
        val enabledWalletSneaker: Boolean = false,
        val enabledRateGmt: Boolean = false,
        val enabledRateGst: Boolean = false,
        val enabledRateSol: Boolean = false,
        val enabledRateUsdc: Boolean = false,
        val enabledRateGem: Boolean = false,
        val enabledRateShoebox: Boolean = false,
        val enabledRateSneaker: Boolean = false,
    ) : BaseContract.State

    /**
     * UIイベント
     *
     */
    sealed class Effect : BaseContract.Effect

    /**
     * アクション
     *
     */
    sealed class Event : BaseContract.Event {
        data class OnUpdateRateAssets(val type: AssetsType) : Event()
        data class OnUpdateWalletAssets(val type: AssetsType) : Event()
        data class OnUpdateSpendingAssets(val type: AssetsType) : Event()

        data class OnChangeRateGmt(val coin: String) : Event()
        data class OnChangeRateGst(val coin: String) : Event()
        data class OnChangeRateSol(val coin: String) : Event()
        data class OnChangeRateUsdc(val coin: String) : Event()
        data class OnChangeRateGem(val sol: String) : Event()
        data class OnChangeRateShoebox(val sol: String) : Event()
        data class OnChangeRateSneaker(val sol: String) : Event()
        data class OnChangeSpendingGmt(val coin: String) : Event()
        data class OnChangeSpendingGst(val coin: String) : Event()
        data class OnChangeSpendingSol(val coin: String) : Event()
        data class OnChangeSpendingGem(val count: String) : Event()
        data class OnChangeSpendingShoebox(val count: String) : Event()
        data class OnChangeSpendingSneaker(val count: String) : Event()
        data class OnChangeWalletGmt(val coin: String) : Event()
        data class OnChangeWalletGst(val coin: String) : Event()
        data class OnChangeWalletSol(val coin: String) : Event()
        data class OnChangeWalletUsdc(val coin: String) : Event()
        data class OnChangeWalletGem(val count: String) : Event()
        data class OnChangeWalletShoebox(val count: String) : Event()
        data class OnChangeWalletSneaker(val count: String) : Event()
    }
}