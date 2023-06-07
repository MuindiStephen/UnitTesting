package com.stevemd.unittesting.viewmodel


import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import com.stevemd.unittesting.data.repository.LoginRepo
import com.stevemd.unittesting.data.repository.LoginRepoTest
import com.stevemd.unittesting.data.responses.LoginResponse
import com.stevemd.unittesting.viemodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {

    @Mock
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepo: LoginRepo
    private val testDispatcher = TestCoroutineDispatcher()


    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp() {
        Dispatchers.setMain(testDispatcher)
        loginRepo = Mockito.mock(LoginRepo::class.java)
        loginViewModel = LoginViewModel(loginRepo)
    }

    @Test
    fun checkLoadingState_OnRequestInit_isTrue() {
        loginViewModel.setLoadingState(true)
        assertThat(loginViewModel.isLoading.value).isEqualTo(true)
    }


    @Test
    fun checkLoadingState_OnRequestComplete_isFalse() {
        loginViewModel.setLoadingState(false)
        assertThat(loginViewModel.isLoading.value).isFalse()
    }

    @Test
    fun onResponseReceived_checkFailedState_isError() {
        Mockito.`when`(loginRepo.validateLoginDetails("test@test.com", "13")).thenReturn(null)
        loginViewModel.onLoginClick("test@test.com", "13")
        assertThat(loginViewModel.error.value).isNotNull()
        assertThat(loginViewModel.isLoading.value).isEqualTo(false)
    }

    @SuppressLint("CheckResult")
    @Test
    fun onResponseReceived_checkSuccessState_isSuccess() {

        val username = "steph@gmail.com"
        val password = "123"

        Mockito.`when`(
            loginRepo.validateLoginDetails(
                username, password
            )
        ).thenReturn(
            LoginResponse()
        )

        loginViewModel.onLoginClick(username, password)
        assertThat(loginViewModel.responseDataLD.value !=null)
        assertThat(loginViewModel.error.value.isNullOrBlank()).isEqualTo(true)
        assertThat(loginViewModel.isLoading.value).isEqualTo(false)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}