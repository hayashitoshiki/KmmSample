package com.myapp.test

import com.myapp.model.entity.AllAsset
import com.myapp.model.entity.Spending
import com.myapp.model.entity.StepnCoinRate
import com.myapp.model.entity.Wallet
import com.myapp.model.value.*

object MockUtil {

    private val DEFAULT_GST = GstCoin(1f)
    private val DEFAULT_GMT = GmtCoin(2f)
    private val DEFAULT_SOL = SolanaCoin(3f)
    private val DEFAULT_USDC = UsdcCoin(4f)
    private val DEFAULT_GEM = GemAssets(5f)
    private val DEFAULT_SHOEBOX = ShoeboxAssets(6f)
    private val DEFAULT_SNEAKER = SneakerAssets(7f)

    enum class Characters(val value: String) {
        ROMAJI_SMALL_HALF("a"),
        ROMAJI_SMALL_FULL("ａ"),
        ROMAJI_BIG_HALF("A"),
        ROMAJI_BIG_FULL("Ａ"),
        NUMBER_HALF("9"),
        NUMBER_FULL("９"),
        NUMBER_FLOAT("1.9"),
        HIRAGANA("あ"),
        KATAKANA_HALF("ｱ"),
        KATAKANA_FULL("ア"),
        SPACE_HALF(" "),
        SPACE_FULL("　"),
        SYMBOL("▲"),
        PERIOD("."),
        COMMA(","),
        EMPTY(""),
        NOW_LINE("\n")
    }

    val assetsLists = listOf(
        DEFAULT_GST,
        DEFAULT_GMT,
        DEFAULT_SOL,
        DEFAULT_USDC ,
        DEFAULT_GEM,
        DEFAULT_SHOEBOX,
        DEFAULT_SNEAKER
    )



    /**
     * 資産の基底クラス
     *
     */
    sealed class Assets {
        abstract val value: Float
        abstract fun type() : AssetsType
    }

    fun getSpendingCoin(
        gst: GstCoin = DEFAULT_GST,
        gmt: GmtCoin = DEFAULT_GMT,
        sol: SolanaCoin = DEFAULT_SOL,
        gem: GemAssets = DEFAULT_GEM,
        shoebox: ShoeboxAssets = DEFAULT_SHOEBOX,
        sneaker: SneakerAssets = DEFAULT_SNEAKER
    ) : Spending {

        return Spending(
            gst = gst,
            gmt = gmt,
            sol = sol,
            gem = gem,
            shoebox = shoebox,
            sneaker = sneaker
        )
    }

    fun getWalletCoin(
        gst: GstCoin = DEFAULT_GST,
        gmt: GmtCoin = DEFAULT_GMT,
        sol: SolanaCoin = DEFAULT_SOL,
        usdc: UsdcCoin = DEFAULT_USDC,
        gem: GemAssets = DEFAULT_GEM,
        shoebox: ShoeboxAssets = DEFAULT_SHOEBOX,
        sneaker: SneakerAssets = DEFAULT_SNEAKER
    ) : Wallet {
        return Wallet(
            gst = gst,
            gmt = gmt,
            sol = sol,
            usdc = usdc,
            gem = gem,
            shoebox = shoebox,
            sneaker = sneaker
        )
    }

    fun getRateCoin(
        gst: GstCoin = DEFAULT_GST,
        gmt: GmtCoin = DEFAULT_GMT,
        sol: SolanaCoin = DEFAULT_SOL,
        usdc: UsdcCoin = DEFAULT_USDC,
        gem: GemAssets = DEFAULT_GEM,
        shoebox: ShoeboxAssets = DEFAULT_SHOEBOX,
        sneaker: SneakerAssets = DEFAULT_SNEAKER
    ) : StepnCoinRate {
        return StepnCoinRate(
            gst = gst,
            gmt = gmt,
            sol = sol,
            usdc = usdc,
            gem = gem,
            shoebox = shoebox,
            sneaker = sneaker
        )
    }

    fun getAllAsset(
        gst: GstCoin = DEFAULT_GST,
        gmt: GmtCoin = DEFAULT_GMT,
        sol: SolanaCoin = DEFAULT_SOL,
        usdc: UsdcCoin = DEFAULT_USDC,
        gem: GemAssets = DEFAULT_GEM,
        shoebox: ShoeboxAssets = DEFAULT_SHOEBOX,
        sneaker: SneakerAssets = DEFAULT_SNEAKER
    ) : AllAsset {
        return AllAsset(
            gst = gst,
            gmt = gmt,
            sol = sol,
            usdc = usdc,
            gem = gem,
            shoebox = shoebox,
            sneaker = sneaker
        )
    }

}
