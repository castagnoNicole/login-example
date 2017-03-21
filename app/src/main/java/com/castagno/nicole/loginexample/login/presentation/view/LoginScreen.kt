package com.castagno.nicole.loginexample.login.presentation.view

import com.castagno.nicole.loginexample.common.presentation.View

// The "contract" every Login screen must satisfy
interface LoginScreen : View {
    fun showLoginSuccessfulScreen()
    fun showLoginError()
}
