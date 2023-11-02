package com.bogdash.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bogdash.bulletinboard.R
import com.bogdash.bulletinboard.databinding.ActivityEditsAdsBinding
import com.bogdash.bulletinboard.dialogs.DialogSpinnerHelper
import com.bogdash.bulletinboard.utils.CityHelper

class EditsAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditsAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditsAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCountry = CityHelper.getAllCountries(this)
        val dialog = DialogSpinnerHelper()
        dialog.showSpinnerDialog(this, listCountry)
    }
}