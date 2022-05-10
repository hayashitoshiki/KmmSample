package com.myapp.model.value

/**
 * 金融資産
 *
 */
sealed class StepnCoin : Assets() {
    abstract override val value: Float
    abstract override fun type() : StepnCoinType
}

data class GmtCoin(override val value: Float) : StepnCoin() {
    override fun type() = StepnCoinType.GMT
}

data class GstCoin(override val value: Float) : StepnCoin() {
    override fun type() = StepnCoinType.GST
}

data class SolanaCoin(override val value: Float) : StepnCoin() {
    override fun type() = StepnCoinType.SOL
}

data class UsdcCoin(override val value: Float) : StepnCoin() {
    override fun type() = StepnCoinType.USCD
}