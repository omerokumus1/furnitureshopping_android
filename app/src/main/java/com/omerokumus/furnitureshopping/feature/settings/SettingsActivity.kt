package com.omerokumus.furnitureshopping.feature.settings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.omerokumus.furnitureshopping.R
import com.omerokumus.furnitureshopping.base.compat.LanguageCompat
import com.omerokumus.furnitureshopping.base.compat.LocaleCompat
import com.omerokumus.furnitureshopping.databinding.ActivitySettingsBinding
import com.omerokumus.furnitureshopping.extensions.setAppLanguage
import java.util.Locale

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var isSpinnerInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        savedInstanceState?.let {
            isSpinnerInitialized = it.getBoolean(::isSpinnerInitialized.name)
        }
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBackButton()
        setupSpinner()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(::isSpinnerInitialized.name, isSpinnerInitialized)
    }

    private fun setUpBackButton() {
        binding.backBtn.setOnClickListener { finish() }
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.language_options,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.spinner.adapter = adapter
            setInitialLanguageValue(adapter)
        }
        binding.spinner.onItemSelectedListener = getOnItemSelectedListener()
    }

    private fun getOnItemSelectedListener() = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>,
            view: View?,
            position: Int,
            id: Long
        ) {
            val selectedLanguage = parent.getItemAtPosition(position).toString()
            when (selectedLanguage) {
                "English" -> setAppLanguage(Locale.ENGLISH)
                "Türkçe" -> setAppLanguage(LocaleCompat.TURKISH)
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

    private fun setInitialLanguageValue(adapter: ArrayAdapter<CharSequence>) {
        if (isSpinnerInitialized) return
        val initialLanguage = when (LanguageCompat.getAppLanguage(this)) {
            "en" -> "English"
            "tr" -> "Türkçe"
            else -> "English"
        }

        val position = adapter.getPosition(initialLanguage)

        if (position != -1) {
            binding.spinner.setSelection(position, false)
        }
        isSpinnerInitialized = true
    }


}