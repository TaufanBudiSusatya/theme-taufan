package com.example.theme_taufan

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.theme_taufan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.changeTheme = this
        checkToTheme()
    }

    fun chooseThemeDialog() {
        //membuat tampilan seperti jendela yang akan muncul di layar
        val builder = AlertDialog.Builder(this)
        //dengan judul Choose themme
        builder.setTitle(getString(R.string.choose_theme_text))
        //variable themes menampung array
        val themes = arrayOf(LIGHT, DARK, DEFAULT)
        val checkedItem = MyPreferences(this).darkMode

        //menentapkan dari daftar item yang akan di tampilkan
        builder.setSingleChoiceItems(themes, checkedItem) { dialog, which ->
            //jika zero maka mode gelap di non aktifkan
            when (which) {
                ZERO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    MyPreferences(this).darkMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                //jika one maka mode gelap di nyalakan
                ONE -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    MyPreferences(this).darkMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }

                //dan jika two maka mengikuti sesuai system di hp
                TWO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    MyPreferences(this).darkMode = 2
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    //melakukan pengecekkan untuk theme yang di pilih
    //apakah mode malam mati/nyala atau sesuai system hp
    private fun checkToTheme() {
        when (MyPreferences(this).darkMode) {
            ZERO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            ONE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            TWO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }

    //membuat variabel di kelas object dan mendeklarasikannya
    companion object {
        private const val LIGHT = "Light Theme"
        private const val DARK = "Dark Theme"
        private const val DEFAULT = "System Default Theme"
        private const val ZERO = 0
        private const val ONE = 1
        private const val TWO = 2
    }
}