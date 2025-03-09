package com.omerokumus.furnitureshopping.extensions

import androidx.fragment.app.Fragment
import com.omerokumus.furnitureshopping.base.compat.LanguageCompat
import java.util.Locale


fun Fragment.setAppLanguage(locale: Locale) {
    context?.let {
        LanguageCompat.setAppLanguage(it, locale)
    }
}
