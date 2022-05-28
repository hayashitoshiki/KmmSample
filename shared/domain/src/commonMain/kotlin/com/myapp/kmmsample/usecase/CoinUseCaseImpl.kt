package com.myapp.kmmsample.usecase

import com.myapp.kmmsample.repository.LocalCoinRepository
import com.myapp.model.entity.AllAsset
import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.*

expect class CoinUseCaseImpl(localCoinRepository: LocalCoinRepository
) : CoinUseCase {
    val localCoinRepository: LocalCoinRepository

    override fun getAllAsset() : AllAsset

    override fun getWalletCoin(): Wallet

    override fun getSpendingCoin(): Spending

    override fun getRateCoin(legalTender: LegalTender): StepnCoinRate

    override fun updateWalletAssets(assets: Assets)

    override fun updateSpendingAssets(assets: Assets)

    override fun updateRateAssets(assets: Assets)

    override fun changeStableRate(assets: Assets, legalTender: LegalTender) : Float
}