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

    fun getAllAsset() : AllAsset
    fun getWalletCoin() : Wallet
    fun getSpendingCoin() : Spending
    fun getRateCoin(legalTender: LegalTender) : StepnCoinRate

    fun updateWalletAssets(assets: Assets)
    fun updateSpendingAssets(assets: Assets)
    fun updateRateAssets(assets: Assets)

    fun changeStableRate(assets: Assets, legalTender: LegalTender) : Float
}