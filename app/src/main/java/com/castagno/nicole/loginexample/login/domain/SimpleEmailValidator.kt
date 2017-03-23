package com.castagno.nicole.loginexample.login.domain

class SimpleEmailValidator : EmailValidator {
    override fun validate(email: String): Boolean {
        return email.contains("@")
    }
}
