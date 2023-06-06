package com.stevemd.unittesting.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorTest {
    @Test
    fun `validates email if in correct pattern` () {
        val result = Validator.validateEmail("stephenmuindi241@gmail.com")
        assertThat(result).isTrue()
    }

    @Test
    fun `validates email pattern is not correct` () {
        val result = Validator.validateEmail("@gmail.com")
        assertThat(result).isFalse()
    }
}