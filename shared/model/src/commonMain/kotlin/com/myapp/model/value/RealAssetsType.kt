package com.myapp.model.value

/**
 * 実物資産の種別
 *
 * @property label 表示用ラベル
 */
sealed class RealAssetsType(label: String) : AssetsType(label) {
    object GEM : RealAssetsType("Gem")
    object SHOEBOX : RealAssetsType("Shoebox")
    object SNEAKER : RealAssetsType("Sneaker")
}
