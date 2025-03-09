package com.omerokumus.furnitureshopping.extensions

import androidx.appcompat.app.AppCompatActivity
import com.omerokumus.furnitureshopping.base.compat.LanguageCompat
import java.util.Locale


fun AppCompatActivity.setAppLanguage(locale: Locale) {
    LanguageCompat.setAppLanguage(this, locale)
}
