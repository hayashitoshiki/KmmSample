package com.myapp.ui.util


/**
 * 数値に関するUtilクラス
 */
object NumberUtil {

    /**
     * コインの値をString型からFloat型へ変更
     *
     * @param value String型のコインの値
     * @return Float型のコインの値
     */
    fun changeCoinValue(value: String) : Float {
        return if (value.endsWith(".")) {
            (value + "0").toFloat()
        } else {
            value.toFloat()
        }
    }

    /**
     * コインの更新の有無判定
     *
     * @param beforeValue 変更前の値
     * @param afterValue 変更後の値
     * @return コインの更新の有無
     */
    fun checkCoinUpdate(beforeValue: String, afterValue: String) : Boolean {
        return beforeValue != afterValue && afterValue.toFloatOrNull() != null
    }

    /**
     * コインの入力判定
     *
     * @param value 入力値
     * @return 入力判定結果
     */
    fun checkCoinInput(value: String) : Boolean {
        val regexNumber = "[0-9.]{1,8}".toRegex()

        if (value.isEmpty()) return true
        if (!regexNumber.matches(value)) return false
        value.chunked(1)
            .filter { it == "." }
            .size
            .also{ if(it >= 2) return false }
        return true
    }

    /**
     * カウントの入力判定
     *
     * @param value 入力値
     * @return 判定結果
     */
    fun checkCountInput(value: String) : Boolean {
        val regexNumber = "[0-9]{1,8}".toRegex()

        if (value.isEmpty()) return true
        if (!regexNumber.matches(value)) return false
        return true
    }
}