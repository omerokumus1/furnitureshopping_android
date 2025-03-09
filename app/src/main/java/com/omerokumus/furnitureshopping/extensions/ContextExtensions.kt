package com.omerokumus.furnitureshopping.extensions

import android.content.Context
import com.omerokumus.furnitureshopping.base.compat.LanguageCompat
import java.util.Locale


fun Context?.setAppLanguage(locale: Locale) {
    this?.let {
        LanguageCompat.setAppLanguage(it, locale)
    }
}
