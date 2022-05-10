package com.myapp.model.value

/**
 * 実物資産
 *
 */
sealed class RealAssets : Assets() {
    abstract override val value: Float
    abstract override fun type() : RealAssetsType
}
data class SneakerAssets(override val value: Float) : RealAssets() {
    override fun type() = RealAssetsType.SNEAKER
}

data class GemAssets(override val value: Float) : RealAssets() {
    override fun type() = RealAssetsType.GEM
}

data class ShoeboxAssets(override val value: Float) : RealAssets() {
    override fun type() = RealAssetsType.SHOEBOX
}

