package com.bogdash.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bogdash.bulletinboard.databinding.ActivityEditsAdsBinding
import com.bogdash.bulletinboard.dialogs.DialogSpinnerHelper
import com.bogdash.bulletinboard.utils.CityHelper

class EditsAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditsAdsBinding
    private val dialog = DialogSpinnerHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditsAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init() {
        val listCountry = CityHelper.getAllCountries(this)
        dialog.showSpinnerDialog(this, listCountry)
    }
}