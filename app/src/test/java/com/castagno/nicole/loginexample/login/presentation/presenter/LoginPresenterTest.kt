package com.castagno.nicole.loginexample.login.presentation.presenter

import com.castagno.nicole.loginexample.login.domain.EmailValidator
import com.castagno.nicole.loginexample.login.presentation.view.LoginScreen
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LoginPresenterTest {
    private lateinit var presenter: LoginPresenter

    private val view: LoginScreen = mock(LoginScreen::class.java)
    private val tracker: EventTracker = mock(EventTracker::class.java)
    private val emailValidator: EmailValidator = mock(EmailValidator::class.java)

    @Before //run this before every test
    fun setUp() {
        presenter = LoginPresenter(view, tracker, emailValidator)
    }

    @Test
    fun onViewReady_LogsAnEvent() {
        presenter.onViewReady()

        verify(tracker).trackEvent(Event("view_page", "login_screen"))
    }

    @Test
    fun onEmailEntered_IncorrectEmail_ShowsError() { //funcName_Status_WhatIsGoningToHappenc
        // given: emailValidator returns false when validating ":D"
        val invalidEmail = ":D"
        `when`(emailValidator.validate(invalidEmail)).thenReturn(false)

        // when: onEmail is called with invalid email
        presenter.onEmailEntered(invalidEmail)

        // then: view should show incorrect email error
        verify(view).showIncorrectEmailError()
    }

    @Test
    fun onEmailEntered_CorrectEmail_DoesntShowError() {
        // given
        val validEmail = "hello@remente.com"
        `when`(emailValidator.validate(validEmail)).thenReturn(true)

        // when
        presenter.onEmailEntered(validEmail)

        // then
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
