package com.myapp.kmmsample.repository

import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.*

/**
 * STEP'Nのコインに関するローカルCRUD処理
 *
 */
interface LocalCoinRepository {

    fun getWalletCoin() : Wallet
    fun getSpendingCoin() : Spending
    fun getRateCoin() : StepnCoinRate

    fun updateWalletGmt(coin: GmtCoin)
    fun updateWalletGst(coin: GstCoin)
    fun updateWalletSol(coin: SolanaCoin)
    fun updateWalletUsdc(coin: UsdcCoin)
    fun updateWalletGem(assets: GemAssets)
    fun updateWalletShoebox(assets: ShoeboxAssets)
    fun updateWalletSneaker(assets: SneakerAssets)

    fun updateRateGmt(coin: GmtCoin)
    fun updateRateGst(coin: GstCoin)
    fun updateRateSol(coin: SolanaCoin)
    fun updateRateUsdc(coin: UsdcCoin)
    fun updateRateGem(assets: GemAssets)
    fun updateRateShoebox(assets: ShoeboxAssets)
    fun updateRateSneaker(assets: SneakerAssets)

    fun updateSpendingGmt(coin: GmtCoin)
    fun updateSpendingGst(coin: GstCoin)
    fun updateSpendingSol(coin: SolanaCoin)
    fun updateSpendingGem(assets: GemAssets)
    fun updateSpendingShoebox(assets: ShoeboxAssets)
    fun updateSpendingSneaker(assets: SneakerAssets)

}