package com.stevemd.unittesting.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false` (){
        val result = RegistrationUtil.validRegistrationInput(
            userName = "",
            password = "12345678",
            confirmPassword = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `all input fields are not empty` (){
        val result = RegistrationUtil.validRegistrationInput(
            userName = "Muindi",
            password = "10011001",
            confirmPassword = "10011001"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `empty passwords return false`(){
        val result = RegistrationUtil.validRegistrationInput(
            userName = "Steph",
            password = "",
            confirmPassword = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `unmatched passwords returns false`() {
        val result = RegistrationUtil.validRegistrationInput(
            userName = "Muindi",
            password = "12345678",
            confirmPassword = "12340000"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password length is incorrect`() {
        val result = RegistrationUtil.validRegistrationInput(
            userName = "Muindi Steve",
            password = "123456",
            confirmPassword = "123456"
        )
        assertThat(result).isFalse()
    }

}