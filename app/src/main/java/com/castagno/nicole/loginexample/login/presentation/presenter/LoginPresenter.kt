package com.castagno.nicole.loginexample.login.presentation.presenter

import android.util.Log
import com.castagno.nicole.loginexample.common.presentation.Presenter
import com.castagno.nicole.loginexample.login.presentation.view.LoginScreen

// Class representing an event (storing useful information in a single object to improve readability)
data class Event(val name: String, val argument: Any)

// Interface for an event tracker (allows you to give classes various different types of event trackers)
interface EventTracker {
    fun trackEvent(event: Event)
}

class Something(val loginPresenter: LoginPresenter, val eventTracker: EventTracker) // <-- Silly
class LoginPresenterEvents(val tracker: EventTracker) // <-- Also silly

// NOTE: Do not call it "Tracker" or "LogTracker" - we want as much information as possible!
class LogCatEventTracker : EventTracker { // <-- PERFECT
    override fun trackEvent(event: Event) {
        Log.i("Tracker", event.toString())
    }
}

// Presenter for a LoginScreen - can interact with anything that "looks" like a LoginScreen
// (i.e. implements LoginScreen interface)
class LoginPresenter(view: LoginScreen, private val tracker: EventTracker) : Presenter<LoginScreen>(view) {
    override fun onViewReady() {
        tracker.trackEvent(Event("view_page", "login_screen"))
        //view.showLoginError()
    }

    override fun onViewDestroy() {
        tracker.trackEvent(Event("Hello", 3))
    }

    fun onEmailEntered(email: String) {
        if (!email.contains("@")) {
            view.showIncorrectEmailError()
        }
    }

    fun onPasswordEntered(password: String) {
        if (password.length < 5) {
            view.showIncorrectPasswordError()
        }
    }
}
