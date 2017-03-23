package com.castagno.nicole.loginexample.login.presentation.view

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.castagno.nicole.loginexample.R
import com.castagno.nicole.loginexample.login.presentation.presenter.LogCatEventTracker
import com.castagno.nicole.loginexample.login.presentation.presenter.LoginPresenter

class MainActivity : AppCompatActivity() {

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

        val view = LoggingLoginScreen()
        val tracker = LogCatEventTracker()
        //val presenter = LoginPresenter(view, tracker)
        //presenter.onViewReady()
    }

}
