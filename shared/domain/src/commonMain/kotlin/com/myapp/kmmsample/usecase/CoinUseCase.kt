package com.myapp.kmmsample.usecase

import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.Assets

/**
 * STEP'Nのコインに関する業務ロジック
 *
 */
interface CoinUseCase {

    fun getWalletCoin() : Wallet
    fun getSpendingCoin() : Spending
    fun getRateCoin() : StepnCoinRate

    fun updateWalletAssets(assets: Assets)
    fun updateSpendingAssets(assets: Assets)
    fun updateRateAssets(assets: Assets)
}