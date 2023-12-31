package com.bogdash.bulletinboard.accounthelper

import android.util.Log
import android.widget.Toast
import com.bogdash.bulletinboard.MainActivity
import com.bogdash.bulletinboard.R
import com.bogdash.bulletinboard.constants.FirebaseAuthConstants
import com.bogdash.bulletinboard.dialoghelper.GoogleAccConst
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AccountHelper(act: MainActivity) {
    private val act = act
    private lateinit var signInClient: GoogleSignInClient
    fun signUpWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.myAuthentication
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sendEmailVerification(task.result?.user!!)
                        act.uiUpdate(task.result?.user)
                    } else {
//                        Toast.makeText(act, act.resources.getString(R.string.sign_up_error), Toast.LENGTH_LONG).show()
                        Log.d("MyLog", "Exception: " + task.exception)
                        when (task.exception) {

                            is FirebaseAuthUserCollisionException -> {
                                val exception = task.exception as FirebaseAuthUserCollisionException
                                if (exception.errorCode == FirebaseAuthConstants.ERROR_EMAIL_ALREADY_IN_USE) {
                                    Toast.makeText(
                                        act,
                                        FirebaseAuthConstants.ERROR_EMAIL_ALREADY_IN_USE,
                                        Toast.LENGTH_LONG
                                    ).show()
                                    // Link email
                                    linkEmailToGoogle(email, password)
                                }
                            }

                            is FirebaseAuthInvalidCredentialsException -> {
                                val exception =
                                    task.exception as FirebaseAuthInvalidCredentialsException
                                if (exception.errorCode == FirebaseAuthConstants.ERROR_INVALID_EMAIL) {
                                    Toast.makeText(
                                        act,
                                        FirebaseAuthConstants.ERROR_INVALID_EMAIL,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }

                        if (task.exception is FirebaseAuthWeakPasswordException) {
                            val exception = task.exception as FirebaseAuthWeakPasswordException
                            Log.d("MyLog", "Exception: ${exception.errorCode}")
                            if (exception.errorCode == FirebaseAuthConstants.ERROR_WEAK_PASSWORD) {
                                Toast.makeText(
                                    act,
                                    FirebaseAuthConstants.ERROR_WEAK_PASSWORD,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
        }
    }

    fun signInWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.myAuthentication
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        act.uiUpdate(task.result?.user)
                    } else {
                        Log.d("MyLog", "Google Sign In Exception: ${task.exception}")
//                        Toast.makeText(act, act.resources.getString(R.string.sign_in_error), Toast.LENGTH_LONG).show()
                        when (task.exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                val exception =
                                    task.exception as FirebaseAuthInvalidCredentialsException
                                when (exception.errorCode) {
                                    FirebaseAuthConstants.ERROR_INVALID_EMAIL -> {
                                        Toast.makeText(
                                            act,
                                            FirebaseAuthConstants.ERROR_INVALID_EMAIL,
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }

                                    FirebaseAuthConstants.ERROR_WRONG_PASSWORD -> {
                                        Toast.makeText(
                                            act,
                                            FirebaseAuthConstants.ERROR_WRONG_PASSWORD,
                                            Toast.LENGTH_LONG
                                        ).show()

                                    }
                                }
                            }

                            is FirebaseAuthInvalidUserException -> {
                                val exception = task.exception as FirebaseAuthInvalidUserException
                                if (exception.errorCode == FirebaseAuthConstants.ERROR_USER_NOT_FOUND) {
                                    Toast.makeText(
                                        act,
                                        FirebaseAuthConstants.ERROR_USER_NOT_FOUND,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                        }
                    }
                }
        }
    }

    private fun linkEmailToGoogle(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        if (act.myAuthentication.currentUser != null) {
            act.myAuthentication.currentUser?.linkWithCredential(credential)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            act,
                            act.resources.getString(R.string.link_done),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        } else {
            Toast.makeText(
                act,
                act.resources.getString(R.string.enter_to_google),
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun getSignInClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(act.getString(R.string.default_web_client_id)).requestEmail().build()
        return GoogleSignIn.getClient(act, gso)
    }

    fun signInWithGoogle() {
        signInClient = getSignInClient()
        val intent = signInClient.signInIntent
        act.startActivityForResult(intent, GoogleAccConst.GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    fun signOutGoogle() {
        getSignInClient().signOut()
    }

    fun signInFirebaseWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        act.myAuthentication.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(act, "Sign In done!", Toast.LENGTH_LONG).show()
                act.uiUpdate(task.result?.user)
            } else {
                Log.d("MyLog", "Google Sign In Exception: ${task.exception}")
            }
        }
    }


    private fun sendEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    act,
                    act.resources.getString(R.string.send_verification_done),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    act,
                    act.resources.getString(R.string.send_verification_email_error),
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

}