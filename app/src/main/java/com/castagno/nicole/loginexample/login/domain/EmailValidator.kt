package com.castagno.nicole.loginexample.login.domain

interface EmailValidator {
    fun validate(email: String): Boolean
}

