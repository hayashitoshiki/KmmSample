package com.myapp.repository

import com.myapp.kmmsample.repository.LocalCoinRepository
import com.myapp.local.PreferenceManager
import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.*
import javax.inject.Inject

class LocalCoinRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
): LocalCoinRepository {

    override fun getWalletCoin(): Wallet {
        val gst = GstCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_GST))
        val gmt = GmtCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_GMT))
        val sol = SolanaCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_SOL))
        val usdc = UsdcCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_USDC))
        val gem = GemAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_GEM))
        val shoebox = ShoeboxAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_SHOEBOX))
        val sneaker = SneakerAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.WALLET_SNEAKER))
      return Wallet(gst, gmt, sol, usdc, gem, shoebox, sneaker)
    }

    override fun getSpendingCoin(): Spending {
        val gst = GstCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.SPENDING_GST))
        val gmt = GmtCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.SPENDING_GMT))
        val sol = SolanaCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.SPENDING_SOL))
        val gem = GemAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.SPENDING_GEM))
        val shoebox = ShoeboxAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.SPENDING_SHOEBOX))
        val sneaker = SneakerAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.SPENDING_SNEAKER))
        return Spending(gst, gmt, sol, gem, shoebox, sneaker)
    }

    override fun getRateCoin(): StepnCoinRate {
        val gst = GstCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_GST))
        val gmt = GmtCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_GMT))
        val sol = SolanaCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_SOL))
        val usdc = UsdcCoin(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_USDC))
        val gem = GemAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_GEM))
        val shoebox = ShoeboxAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_SHOEBOX))
        val sneaker = SneakerAssets(preferenceManager.getFloat(PreferenceManager.Key.FloatKey.RATE_SNEAKER))
        return StepnCoinRate(gst, gmt, sol, usdc, gem, shoebox, sneaker)
    }

    override fun updateWalletGmt(coin: GmtCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_GMT, coin.value)
    }

    override fun updateWalletGst(coin: GstCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_GST, coin.value)
    }

    override fun updateWalletSol(coin: SolanaCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_SOL, coin.value)
    }

    override fun updateWalletUsdc(coin: UsdcCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_USDC, coin.value)
    }

    override fun updateWalletGem(assets: GemAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_GEM, assets.value)
    }

    override fun updateWalletShoebox(assets: ShoeboxAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_SHOEBOX, assets.value)
    }

    override fun updateWalletSneaker(assets: SneakerAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.WALLET_SNEAKER, assets.value)
    }

    override fun updateRateGmt(coin: GmtCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_GMT, coin.value)
    }

    override fun updateRateGst(coin: GstCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_GST, coin.value)
    }

    override fun updateRateSol(coin: SolanaCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_SOL, coin.value)
    }

    override fun updateRateUsdc(coin: UsdcCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_USDC, coin.value)
    }

    override fun updateRateGem(assets: GemAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_GEM, assets.value)
    }

    override fun updateRateShoebox(assets: ShoeboxAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_SHOEBOX, assets.value)
    }

    override fun updateRateSneaker(assets: SneakerAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.RATE_SNEAKER, assets.value)
    }

    override fun updateSpendingGmt(coin: GmtCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.SPENDING_GMT, coin.value)
    }

    override fun updateSpendingGst(coin: GstCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.SPENDING_GST, coin.value)
    }

    override fun updateSpendingSol(coin: SolanaCoin) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.SPENDING_SOL, coin.value)
    }
    override fun updateSpendingGem(assets: GemAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.SPENDING_GEM, assets.value)
    }

    override fun updateSpendingShoebox(assets: ShoeboxAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.SPENDING_SHOEBOX, assets.value)
    }

    override fun updateSpendingSneaker(assets: SneakerAssets) {
        preferenceManager.setFloat(PreferenceManager.Key.FloatKey.SPENDING_SNEAKER, assets.value)
    }
}