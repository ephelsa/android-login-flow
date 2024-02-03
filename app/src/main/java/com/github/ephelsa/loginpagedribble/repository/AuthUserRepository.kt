package com.github.ephelsa.loginpagedribble.repository

import com.github.ephelsa.loginpagedribble.domain.User

interface AuthUserRepository {
    suspend fun loginUser(email: String, password: String): Result<Unit>

    suspend fun registerUser(user: User): Result<User>
}