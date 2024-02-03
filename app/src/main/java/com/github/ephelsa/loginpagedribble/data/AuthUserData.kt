package com.github.ephelsa.loginpagedribble.data

import com.github.ephelsa.loginpagedribble.domain.User

interface AuthUserData {
    suspend fun loginUser(username: String, password: String): User

    suspend fun registerUser(user: User): User
}