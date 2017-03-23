package com.castagno.nicole.loginexample.login.presentation.presenter

import com.castagno.nicole.loginexample.login.presentation.view.LoginScreen
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LoginPresenterTest {
    private lateinit var presenter: LoginPresenter

    private val view: LoginScreen = mock(LoginScreen::class.java)
    private val tracker: EventTracker = mock(EventTracker::class.java)

    @Before
    fun setUp() {
        presenter = LoginPresenter(view, tracker)
    }

    @Test
    fun onViewReady_LogsAnEvent() {
        presenter.onViewReady()

        verify(tracker).trackEvent(Event("view_page", "login_screen"))
    }

    @Test
    fun onEmailEntered_IncorrectEmail_ShowsError() {
        presenter.onEmailEntered(":D")

        verify(view).showIncorrectEmailError()
    }

    @Test
    fun onEmailEntered_CorrectEmail_DoesntShowError() {
        presenter.onEmailEntered("hello@remente.com")

        verify(view, never()).showIncorrectEmailError()
    }

    @Test
    fun onPasswordEntered_IncorrectPassword_ShowsError() {
        presenter.onPasswordEntered("")

        verify(view).showIncorrectPasswordError()
    }

    @Test
    fun onPasswordEntered_CorrectPassword_DoesntShowError() {
        presenter.onPasswordEntered("a_good_password")

        verify(view, never()).showIncorrectPasswordError()
    }
}
