package com.castagno.nicole.loginexample.login.domain

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class SimpleEmailValidatorTest {
    private lateinit var validator: SimpleEmailValidator

    @Before
    fun setUp() {
        validator = SimpleEmailValidator()
    }

    @Test
    fun validate_CorrectEmail_ReturnsTrue() {
        // given
        val correctEmail = "hello@remente.com"

        // when
        val result = validator.validate(correctEmail)

        // then
        assertTrue(result)
    }

    @Test
    fun validate_IncorrectEmail_ReturnsFalse() {
        // given
        val incorrectEmail = "hello.com"

        // when
        val result = validator.validate(incorrectEmail)

        // then
        assertFalse(result)
    }

    @Test
    fun validate_EmailEmpty_ReturnsFalse() {
        // given
        val emptyEmail = ""

        // when
        val result = validator.validate(emptyEmail)

        // then
        assertFalse(result)
    }
}
