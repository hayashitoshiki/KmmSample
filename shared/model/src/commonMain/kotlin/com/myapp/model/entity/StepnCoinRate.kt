package com.myapp.model.entity

import com.myapp.model.value.*

/**
 * STEP'Nの各種コインのレート
 *
 * @property gst GSTコイン
 * @property gmt GMTコイン
 * @property sol Solanaコイン
 * @property usdc USDコイン
 */
data class StepnCoinRate(
    var gst: GstCoin,
    var gmt: GmtCoin,
    var sol: SolanaCoin,
    var usdc: UsdcCoin,
    var gem: GemAssets,
    var shoebox: ShoeboxAssets,
    var sneaker: SneakerAssets
) {
    fun getRate(type: AssetsType) = when(type) {
        StepnCoinType.GMT -> gmt
        StepnCoinType.GST -> gst
        StepnCoinType.SOL -> sol
        StepnCoinType.USCD -> usdc
        RealAssetsType.GEM -> gem
        RealAssetsType.SHOEBOX -> shoebox
        RealAssetsType.SNEAKER -> sneaker
    }
}