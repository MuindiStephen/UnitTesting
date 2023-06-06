package com.stevemd.unittesting.data.api

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/** @APIServiceTest
 * Unit Testing remote api service
 */
class ApiServiceTest {
    @Mock
    lateinit var mockWebServer: MockWebServer
    @Mock
    lateinit var apiService: ApiService
    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder().create()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun deconstruct() {
        mockWebServer.shutdown()
    }

    @Test
    fun validateUserData_return_success() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(
                mockResponse.setBody("{ }")
            )
            val response = apiService.validateUserData("","")
            val request = mockWebServer.takeRequest()

            assertThat(request.path).isEqualTo(ApiService.VALIDATE_USER_PATH)

            assertThat(response).isNotNull()
        }
    }

}