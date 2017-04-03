package com.castagno.nicole.loginexample.login.presentation.view

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.icu.text.RelativeDateTimeFormatter
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.transition.Slide
import android.transition.TransitionManager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import butterknife.bindView
import com.castagno.nicole.loginexample.R

class LoginView : RelativeLayout {
    var onLoginClicked: (() -> Unit)? = null
    //var onEmailChanged: ((String) -> Unit)? = null
    //var onPasswordChanged: ((String) -> Unit)? = null

    val email: String
        get() = emailField.text.toString()

    val password: String
        get() = passwordField.text.toString()

    private val emailField: EditText by bindView(R.id.edit_email)
    private val passwordField: EditText by bindView(R.id.edit_password)
    private val loginButton: Button by bindView(R.id.button_login)

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyle: Int) : super(context, attributes, defStyle)

    init {
        inflate(context, R.layout.view_login, this)

        //loginButton.setOnClickListener { onLoginClicked?.invoke() }
        loginButton.setOnClickListener { animateLoginButtonCrazy() }
    }

    // Animate using default transitions (will fade the login button)
    private fun animateLoginButtonFade() {
        TransitionManager.beginDelayedTransition(this)
        loginButton.visibility = View.INVISIBLE
    }

    private fun animateLoginButtonCrazy() {
        val animatorSet = AnimatorSet()

        val translationAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)
        translationAnimator.interpolator = object: Interpolator {
            override fun getInterpolation(input: Float): Float {
                return Math.sin(input.toDouble() * 40).toFloat()
            }
        }
        translationAnimator.duration = 3000
        translationAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            loginButton.translationY = value * 500
        }
        val alphaAnimator = ValueAnimator.ofFloat(1.0f, 0.0f)
        alphaAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            loginButton.alpha = value
        }
        alphaAnimator.startDelay = 2000

        animatorSet.playSequentially(translationAnimator, alphaAnimator)
        animatorSet.start()
    }
}
