package com.example.theme_taufan

import android.content.Context
import androidx.preference.PreferenceManager

class MyPreferences(context: Context) {

    //Mengembalikan nama yang digunakan untuk menyimpan preferensi bersama default
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    //mengambil nilai preferensi berupa int jika ada
    var darkMode = preferences.getInt(DARK_STATUS, ZERO)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()

    //membuat variabel di kelas object dan mendeklarasikannya
    companion object {
        private const val DARK_STATUS = "darkStatus"
        private const val ZERO = 0
    }
}