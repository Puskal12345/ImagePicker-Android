package com.puskal.imagepicker.data.local

import android.content.SharedPreferences
import javax.inject.Inject


class MySharedPreference @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun save(key: String, value: Int) {
        sharedPreferences.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    fun getIntValue(key: String, defaultValue:Int=0): Int = sharedPreferences.getInt(key, defaultValue)

    fun clearPreference() {
        sharedPreferences.edit().clear().apply()
    }

    companion object{
        const val IMAGE_LIST_SIZE="image_list_size"
    }
}