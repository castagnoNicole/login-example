package com.castagno.nicole.loginexample.common.presentation

// All presenters should extend this class and specify FOR WHAT VIEW (V) the presenter is for
abstract class Presenter<V: View>(val view: V) {
    abstract fun onViewReady()
    abstract fun onViewDestroy()
}

