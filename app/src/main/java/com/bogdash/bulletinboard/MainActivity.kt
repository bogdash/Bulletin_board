package com.bogdash.bulletinboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bogdash.bulletinboard.databinding.ActivityMainBinding
import com.bogdash.bulletinboard.dialoghelper.DialogConst
import com.bogdash.bulletinboard.dialoghelper.DialogHelper
import com.bogdash.bulletinboard.dialoghelper.GoogleAccConst
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val dialogHelper = DialogHelper(this)
    val myAuthentication = FirebaseAuth.getInstance()
    private lateinit var tvAccount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GoogleAccConst.GOOGLE_SIGN_IN_REQUEST_CODE) {
//            Log.d("MyLog", "Sign in result")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    dialogHelper.accHelper.signInFirebaseWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Log.d("MyLog", "Api error: ${e.message}")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(myAuthentication.currentUser)
    }

    private fun init() {
        var toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.mainContentInclude.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        tvAccount = binding.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)
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
                dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)
            }
            R.id.sign_up -> {
                dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)
            }
            R.id.sign_out -> {
                uiUpdate(null)
                myAuthentication.signOut()
                dialogHelper.accHelper.signOutGoogle()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?) {
        tvAccount.text = if (user == null) {
            resources.getString(R.string.not_reg)
        } else {
            user.email
        }
    }
}