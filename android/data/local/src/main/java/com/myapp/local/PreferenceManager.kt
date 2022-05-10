package com.myapp.local

import android.content.Context
import javax.inject.Inject

/**
 * Preference制御管理
 */
class PreferenceManager @Inject constructor(val context: Context) {

    companion object{
        private const val PREFERENCE_NAME = "StepnCashManagement"
    }

    /**
     * キー
     *
     */
    class Key {
        /**
         * Float型指定
         */
        enum class FloatKey {
            SPENDING_GST,
            SPENDING_GMT,
            SPENDING_SOL,
            SPENDING_GEM,
            SPENDING_SHOEBOX,
            SPENDING_SNEAKER,
            WALLET_GST,
            WALLET_GMT,
            WALLET_SOL,
            WALLET_USDC,
            WALLET_GEM,
            WALLET_SHOEBOX,
            WALLET_SNEAKER,
            RATE_GST,
            RATE_GMT,
            RATE_SOL,
            RATE_USDC,
            RATE_GEM,
            RATE_SHOEBOX,
            RATE_SNEAKER
        }
    }



    /**
     * Float型格納
     *
     * @param key  キー
     * @param value 格納する値
     */
    fun setFloat(key: Key.FloatKey, value: Float?) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putFloat(key.name, value!!)
        editor.apply()
    }

    /**
     * Float型取得
     *
     * @param key キー
     * @return キーに紐づくFloat型オブジェクト
     */
    fun getFloat(key: Key.FloatKey): Float {
        val defaultValue = 0f
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return preferences.getFloat(key.name, defaultValue)
    }
}