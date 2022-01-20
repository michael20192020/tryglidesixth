package com.hansoft.tryglidesixth

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by QingMei on 2017/8/7.
 * desc:
 */
class Preferences(context: Context) {
    private val sp: SharedPreferences
    var userName: String?
        get() = sp.getString("username", "")
        set(userName) {
            sp.edit().putString("username", userName).apply()
        }

    companion object {
        private const val PREFERENCES_NAME = "Test_Preferences"
    }

    init {
        sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}