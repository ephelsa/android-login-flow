package com.github.ephelsa.loginpagedribble.repository

import com.github.ephelsa.loginpagedribble.data.AuthUserData
import com.github.ephelsa.loginpagedribble.domain.User
import kotlinx.coroutines.delay
import java.lang.Exception

class AuthUserRepositoryImpl(
    private val authUserData: AuthUserData,
) : AuthUserRepository {

    override suspend fun loginUser(email: String, password: String): Result<Unit> {
        delay(300)
        return try {
            authUserData.loginUser(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerUser(user: User): Result<User> {
        return try {
            val user = authUserData.registerUser(user)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
