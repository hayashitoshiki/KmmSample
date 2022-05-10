package com.myapp.model.entity

import com.myapp.model.value.*

/**
 * Wallet
 *
 * @property gst GSTコイン
 * @property gmt GMTコイン
 * @property sol Solanaコイン
 * @property usdc USDコイン
 */
data class Wallet(
    var gst: GstCoin,
    var gmt: GmtCoin,
    var sol: SolanaCoin,
    var usdc: UsdcCoin,
    var gem: GemAssets,
    var shoebox: ShoeboxAssets,
    var sneaker: SneakerAssets,

) {
    fun values() = listOf(gst, gmt, sol, usdc, sneaker, gem, shoebox)
}