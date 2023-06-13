package com.stevemd.unittesting.data.repository

import com.google.common.truth.Truth
import com.stevemd.unittesting.data.responses.LoginResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class LoginRepoTest {

    @Mock
    lateinit var loginRepo: LoginRepo

    @Before
    fun setup() {
        loginRepo = mock(LoginRepo::class.java)
    }

    @Test
    fun validateLogin_isSuccess() {
        runBlocking {
            Mockito.`when`(loginRepo.validateLoginDetails("test@test.com","123")).thenReturn(
                LoginResponse()
            )
        }

        runBlocking {
            Truth.assertThat(loginRepo.validateLoginDetails("test@test.com","123"))
                .isEqualTo(LoginResponse())
        }
    }

    @Test
    fun validateLogin_isFailed() {

        runBlocking {
            Mockito.`when`(loginRepo.validateLoginDetails("test@test.com","12")).thenReturn(
                null
            )
        }

        runBlocking {
            Truth.assertThat(loginRepo.validateLoginDetails("test@test.com","12"))
                .isEqualTo(null)
        }
    }
}