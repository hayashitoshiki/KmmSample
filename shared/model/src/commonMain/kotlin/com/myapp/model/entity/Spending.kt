package com.myapp.model.entity

import com.myapp.model.value.*

/**
 * Spending
 *
 * @property gst GSTコイン
 * @property gmt GMTコイン
 * @property sol Solanaコイン
 * @property gem ジェム（SOL価格）
 * @property shoebox シューズボックス（SOL価格）
 * @property sneaker スニーカー(SOL価格）
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