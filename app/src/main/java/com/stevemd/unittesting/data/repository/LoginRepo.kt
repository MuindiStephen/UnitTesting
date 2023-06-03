package com.stevemd.unittesting.data.repository

import com.stevemd.unittesting.data.responses.LoginResponse

interface LoginRepo {
    fun validateLoginDetails(username: String, pass: String): LoginResponse?
}