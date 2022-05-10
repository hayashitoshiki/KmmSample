package com.myapp.model.value

/**
 * 資産タイプの基底クラス
 *
 * @property label 表示名
 */
sealed class AssetsType(val label: String)

/**
 * 資産の基底クラス
 *
 */
sealed class Assets {
    abstract val value: Float
    abstract fun type() : AssetsType
}