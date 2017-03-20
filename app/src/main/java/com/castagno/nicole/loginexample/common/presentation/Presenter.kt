package com.castagno.nicole.loginexample.common.presentation

abstract class Presenter(val view: View) {
    abstract fun onViewReady()
    abstract fun onViewDestroy()
}

