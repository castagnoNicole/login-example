package com.castagno.nicole.loginexample.login.presentation.view

import android.util.Log

// A MVP view for a login screen, which simply just logs via LogCat.
// This is a perfectly valid MVP view even though it does not actually show any UI
class LoggingLoginScreen : LoginScreen {
    override fun showLoginSuccessfulScreen() {
        Log.i("LoginScreen", "showLoginSuccessfulScreen")
    }

    override fun showLoginError() {
        Log.i("LoginScreen", "showLoginError")
    }

    override fun showIncorrectEmailError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showIncorrectPasswordError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
