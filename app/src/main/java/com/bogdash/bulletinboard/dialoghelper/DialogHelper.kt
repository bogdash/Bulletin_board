package com.bogdash.bulletinboard.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.bogdash.bulletinboard.MainActivity
import com.bogdash.bulletinboard.R
import com.bogdash.bulletinboard.accounthelper.AccountHelper
import com.bogdash.bulletinboard.databinding.SignDailogBinding

class DialogHelper(act: MainActivity) {
    private val act = act
    private val accHelper = AccountHelper(act)
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(act)
        val rootDialogElement = SignDailogBinding.inflate(act.layoutInflater)
        val view = rootDialogElement.root
        builder.setView(view)
        setDialogState(index, rootDialogElement)

        val dialog = builder.create()
        rootDialogElement.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(index, rootDialogElement, dialog)
        }
        rootDialogElement.btForgetPassword.setOnClickListener {
            setOnClickResetPassword(rootDialogElement, dialog)
        }


        dialog.show()
    }

    private fun setOnClickResetPassword(
        rootDialogElement: SignDailogBinding,
        dialog: AlertDialog?
    ) {
        if (rootDialogElement.edSignEmail.text.isNotEmpty()) {
            act.myAuthentication
                .sendPasswordResetEmail(rootDialogElement.edSignEmail.text.toString())
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        Toast.makeText(act, R.string.email_reset_password_was_sent, Toast.LENGTH_LONG).show()
                    }
                }
            dialog?.dismiss()
        } else {
            rootDialogElement.tvDialogMessage.visibility = View.VISIBLE
        }
    }

    private fun setOnClickSignUpIn(
        index: Int,
        rootDialogElement: SignDailogBinding,
        dialog: AlertDialog?
    ) {
        dialog?.dismiss()
        if (index == DialogConst.SIGN_UP_STATE) {

            accHelper.signUpWithEmail(
                rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString()
            )
        } else {
            accHelper.signIpWithEmail(
                rootDialogElement.edSignEmail.text.toString(),
                rootDialogElement.edSignPassword.text.toString()
            )
        }
    }

    private fun setDialogState(index: Int, rootDialogElement: SignDailogBinding) {
        if (index == DialogConst.SIGN_UP_STATE) {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
            rootDialogElement.btForgetPassword.visibility = View.VISIBLE
        }
    }


}