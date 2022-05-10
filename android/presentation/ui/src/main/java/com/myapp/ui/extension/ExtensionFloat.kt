package com.myapp.presentation.extension


/**
 * 表示用の値へ変換
 *
 * @return
 */
fun Float.changeStrValue() : String {
    val str = this.toString()
    val regex = Regex(".0+\$")
    return regex.replace(str, "")
}