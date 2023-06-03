package com.stevemd.unittesting.utils


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorTest {
    @Test
    fun `validates email if in correct pattern` () {
        assertThat(Validator.validateEmail("stephenmuindi241@gmail.com")).isTrue()
    }

    @Test
    fun `validates email pattern is not correct` () {
        assertThat(Validator.validateEmail("@gmail.com")).isFalse()
    }
}