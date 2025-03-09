package com.omerokumus.furnitureshopping.base.compat

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

object LanguageCompat {

    fun setAppLanguage(context: Context, locale: Locale) {
        if (locale.language == getAppLanguage(context)) return
        if (Build.VERSION.SDK_INT >= 33) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(locale.language)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale.language))
        }
    }

    fun getAppLanguage(context: Context): String = if (Build.VERSION.SDK_INT >= 33) {
        context.getSystemService(LocaleManager::class.java).applicationLocales[0].language
    } else {
        context.resources.configuration.locales[0].language
    }

    fun isAppLanguageEnglish(context: Context): Boolean =
        getAppLanguage(context) == Locale.ENGLISH.language

    fun isAppLanguageTurkish(context: Context): Boolean =
        getAppLanguage(context) == LocaleCompat.TURKISH.language
}