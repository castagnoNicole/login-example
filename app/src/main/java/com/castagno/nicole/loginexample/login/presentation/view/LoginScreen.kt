package com.castagno.nicole.loginexample.login.presentation.view

import com.castagno.nicole.loginexample.common.presentation.View

interface LoginScreen : View {
    fun showLoginSuccessfulScreen()
    fun showLoginError()
}
