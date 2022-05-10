package com.myapp.model.entity

import com.myapp.model.value.*

/**
 * Spending
 *
 * @property gst GSTコイン
 * @property gmt GMTコイン
 * @property sol Solanaコイン
 */
data class Spending(
    var gst: GstCoin,
    var gmt: GmtCoin,
    var sol: SolanaCoin,
    var gem: GemAssets,
    var shoebox: ShoeboxAssets,
    var sneaker: SneakerAssets
) {
    fun values() = listOf(gst, gmt, sol, sneaker, gem, shoebox)
}