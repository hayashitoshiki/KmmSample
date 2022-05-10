package com.myapp.model.value

/**
 * 金融資産の種別
 *
 * @property label 表示用ラベル
 */
sealed class StepnCoinType(label: String) : AssetsType(label) {
    object GMT : StepnCoinType("GMT")
    object GST : StepnCoinType("GST")
    object SOL : StepnCoinType("SOL")
    object USCD : StepnCoinType("USDC")
}