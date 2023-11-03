package com.bogdash.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bogdash.bulletinboard.R
import com.bogdash.bulletinboard.databinding.ActivityEditsAdsBinding
import com.bogdash.bulletinboard.dialogs.DialogSpinnerHelper
import com.bogdash.bulletinboard.utils.CityHelper

class EditsAdsActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditsAdsBinding
    private val dialog = DialogSpinnerHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditsAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init() {

    }

    // onClicks
    fun onClickSelectCountry(view: View) {
        val listCountry = CityHelper.getAllCountries(this)
        dialog.showSpinnerDialog(this, listCountry, binding.tvCountry)

        if (binding.tvCity.text.toString() != getString(R.string.select_city)) {
            binding.tvCity.text = getString(R.string.select_city)
        }
    }

    fun onClickSelectCity(view: View) {
        val selectedCountry = binding.tvCountry.text.toString()
        if (selectedCountry != getString(R.string.select_country)) {
            val listCity = CityHelper.getAllCities(selectedCountry, this)
            dialog.showSpinnerDialog(this, listCity, binding.tvCity)
        } else {
            Toast.makeText(this, "No country selected", Toast.LENGTH_LONG).show()
        }

    }
}