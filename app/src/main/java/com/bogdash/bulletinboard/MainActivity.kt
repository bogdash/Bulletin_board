package com.bogdash.bulletinboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bogdash.bulletinboard.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        var toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.mainContentInclude.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.my_ads -> {
                Toast.makeText(this, "Pressed my ads", Toast.LENGTH_LONG).show()
            }
            R.id.car -> {
                Toast.makeText(this, "Pressed car", Toast.LENGTH_LONG).show()
            }
            R.id.pc -> {
                Toast.makeText(this, "Pressed pc", Toast.LENGTH_LONG).show()
            }
            R.id.smartphone -> {
                Toast.makeText(this, "Pressed smartphone", Toast.LENGTH_LONG).show()
            }
            R.id.household_appliances -> {
                Toast.makeText(this, "Pressed household_appliances", Toast.LENGTH_LONG).show()
            }
            R.id.sign_in -> {
                Toast.makeText(this, "Pressed sign in", Toast.LENGTH_LONG).show()

            }
            R.id.sign_up -> {
                Toast.makeText(this, "Pressed sign up", Toast.LENGTH_LONG).show()

            }
            R.id.sign_out -> {
                Toast.makeText(this, "Pressed sign out", Toast.LENGTH_LONG).show()

            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}