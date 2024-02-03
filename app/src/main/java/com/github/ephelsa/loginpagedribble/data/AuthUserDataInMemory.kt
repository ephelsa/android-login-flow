package com.github.ephelsa.loginpagedribble.data

import com.github.ephelsa.loginpagedribble.domain.User

class AuthUserDataInMemory: AuthUserData {
    private val users = mutableSetOf(
        User(
            name = "Leonardo",
            phone = 1,
            email = "leonardo.perez",
            password = "1234"
        )
    )

    override suspend fun loginUser(username: String, password: String): User {
        return users.find { it.email == username && it.password == password }
            ?: throw Exception("User not found")
    }

    override suspend fun registerUser(user: User): User {
        val userFound = users.find { it.email == user.email }

        if (userFound != null) {
            throw Exception("User already exist")
        } else {
            users.add(user)
        }

        return user
    }
}