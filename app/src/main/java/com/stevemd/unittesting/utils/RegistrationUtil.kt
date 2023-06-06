package com.stevemd.unittesting.utils

object RegistrationUtil {

    // Register User
    private val existingUsers = listOf("Stephen", "Faith", "Yusuf", "Sayia")
    fun validRegistrationInput(
        userName: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false
        } else if (userName in existingUsers) {
            return false
        } else if (password != confirmPassword) {
            return false
        } else if (password.length < 8) {
            return false
        } else if (password.count { chars ->
                chars.isDigit()
            } < 8) {
            return false
        }
        return true
    }
}