package com.myapp.model.entity

import com.myapp.model.value.*

/**
 * STEP'Nの各種コインのレート
 *
 * @property gst GSTコイン
 * @property gmt GMTコイン
 * @property sol Solanaコイン
 * @property usdc USDコイン
 * @property gem ジェム（SOL価格）
 * @property shoebox シューズボックス（SOL価格）
 * @property sneaker スニーカー(SOL価格）
 */
data class AllAsset(
    var gst: GstCoin,
    var gmt: GmtCoin,
    var sol: SolanaCoin,
    var usdc: UsdcCoin,
    var gem: GemAssets,
    var shoebox: ShoeboxAssets,
    var sneaker: SneakerAssets
) {
    fun values() = listOf(gst, gmt, sol, usdc, gem, shoebox, sneaker)

}