package com.bogdash.bulletinboard.dialoghelper

import android.app.AlertDialog
import com.bogdash.bulletinboard.MainActivity
import com.bogdash.bulletinboard.R
import com.bogdash.bulletinboard.databinding.SignDailogBinding

class DialogHelper(act: MainActivity) {
    private val act = act
    fun createSignDialog(index: Int) {
        val builder = AlertDialog.Builder(act)
        val rootDialogElement = SignDailogBinding.inflate(act.layoutInflater)
        val view = rootDialogElement.root

        if (index == DialogConst.SIGN_UP_STATE) {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.sign_up)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_up_action)
        } else {
            rootDialogElement.tvSignTitle.text = act.resources.getString(R.string.sign_in)
            rootDialogElement.btSignUpIn.text = act.resources.getString(R.string.sign_in_action)
        }

        builder.setView(view)
        builder.show()
    }
}