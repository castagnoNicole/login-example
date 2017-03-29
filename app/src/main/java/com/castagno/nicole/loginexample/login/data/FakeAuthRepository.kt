package com.castagno.nicole.loginexample.login.data

import com.castagno.nicole.loginexample.login.domain.AuthRepository
import rx.Completable
import java.lang.Exception
import java.util.concurrent.TimeUnit

class FakeAuthRepository : AuthRepository {
    override fun authenticateWithEmail(email: String, password: String): Completable {
        if (email == "nicole@remente.com" && password == "remente") {
            return Completable.complete()
        } else {
            return Completable.error(Exception())
        }
    }
}

