package com.castagno.nicole.loginexample.login.presentation.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.castagno.nicole.loginexample.R
import com.castagno.nicole.loginexample.login.domain.SimpleEmailValidator
import com.castagno.nicole.loginexample.login.presentation.presenter.LogCatEventTracker
import com.castagno.nicole.loginexample.login.presentation.presenter.LoginPresenter

class MainActivity : AppCompatActivity(), LoginScreen {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val loginView = findViewById(R.id.login) as LoginView

        val tracker = LogCatEventTracker()
        val emailValidator = SimpleEmailValidator()
        val presenter = LoginPresenter(this, tracker, emailValidator)
        presenter.onViewReady()

        loginView.onLoginClicked = {
            val email = loginView.email
            val password = loginView.password
            presenter.onEmailEntered(email)
            presenter.onPasswordEntered(password)
        }
    }

    override fun showLoginSuccessfulScreen() {
        Toast.makeText(this, "showLoginSuccessfulScreen", Toast.LENGTH_SHORT).show()
    }

    override fun showLoginError() {
        Toast.makeText(this, "showLoginError", Toast.LENGTH_SHORT).show()
    }

    override fun showIncorrectEmailError() {
        Toast.makeText(this, "showIncorrectEmailError", Toast.LENGTH_SHORT).show()
    }

    override fun showIncorrectPasswordError() {
        Toast.makeText(this, "showIncorrectPasswordError", Toast.LENGTH_SHORT).show()
    }
}
