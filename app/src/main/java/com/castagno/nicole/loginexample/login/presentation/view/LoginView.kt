package com.castagno.nicole.loginexample.login.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import butterknife.bindView
import com.castagno.nicole.loginexample.R

class LoginView : RelativeLayout {
    var onLoginClicked: (() -> Unit)? = null
    //var onEmailChanged: ((String) -> Unit)? = null
    //var onPasswordChanged: ((String) -> Unit)? = null

    val email: String
        get() = emailField.text.toString()

    val password: String
        get() = passwordField.text.toString()

    private val emailField: EditText by bindView(R.id.edit_email)
    private val passwordField: EditText by bindView(R.id.edit_password)
    private val loginButton: Button by bindView(R.id.button_login)

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(context, attributes, defStyle)

    init {
        inflate(context, R.layout.view_login, this)

        loginButton.setOnClickListener { onLoginClicked?.invoke() }
    }
}
