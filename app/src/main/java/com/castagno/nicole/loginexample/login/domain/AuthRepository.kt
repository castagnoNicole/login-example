package com.castagno.nicole.loginexample.login.domain

import rx.Completable

interface AuthRepository {
    fun authenticateWithEmail(email: String, password: String): Completable
}
