package com.bogdash.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        dialog.showSpinnerDialog(this, listCountry)
    }
}