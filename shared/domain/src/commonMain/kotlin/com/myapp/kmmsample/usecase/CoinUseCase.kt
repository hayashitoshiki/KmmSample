package com.myapp.kmmsample.usecase

import com.myapp.model.entity.AllAsset
import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.Assets
import com.myapp.model.value.LegalTender

/**
 * STEP'Nのコインに関する業務ロジック
 *
 */
interface CoinUseCase {

    /**
     * 保持している全ての資産取得
     *
     * @return 保持している全ての資産
     */
    fun getAllAsset() : AllAsset

    /**
     * Walletで保持している資産取得
     *
     * @return Walletで保持している資産
     */
    fun getWalletCoin() : Wallet

    /**
     * Spendingで保持している資産取得
     *
     * @return Spendingで保持している資産
     */
    fun getSpendingCoin() : Spending

    /**
     * 指定した法定通貨の値に基づいた各種のレート取得
     *
     * @param legalTender 取得する法定通貨の種別
     * @return レート
     */
    fun getRateCoin(legalTender: LegalTender) : StepnCoinRate

    /**
     * Walletの対象の資産アップデート
     *
     * @param assets 更新する資産
     */
    fun updateWalletAssets(assets: Assets)

    /**
     * Spendingの対象の資産アップデート
     *
     * @param assets 更新する資産
     */
    fun updateSpendingAssets(assets: Assets)

    /**
     * 対処の資産のレートのアップデート
     *
     * @param assets 更新するレート値
     */
    fun updateRateAssets(assets: Assets)

    /**
     * 対象の資産を指定した法定通貨のレートで換算して取得
     *
     * @param assets 取得する資産
     * @param legalTender 算出する法定通貨の種別
     * @return 算出結果
     */
    fun changeStableRate(assets: Assets, legalTender: LegalTender) : Float
}