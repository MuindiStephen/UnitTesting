package com.stevemd.unittesting.data.api

import com.stevemd.unittesting.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST(VALIDATE_USER_PATH)
    @FormUrlEncoded
    suspend fun validateUserData(
        @Field("username") username: String,
        @Field("pass") pass: String
    ): LoginResponse


    companion object {
        const val VALIDATE_USER_PATH = "/validate_user"
    }
}
