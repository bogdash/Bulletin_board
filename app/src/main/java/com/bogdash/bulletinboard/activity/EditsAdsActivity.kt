package com.bogdash.bulletinboard.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bogdash.bulletinboard.R
import com.bogdash.bulletinboard.databinding.ActivityEditsAdsBinding

class EditsAdsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditsAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditsAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}