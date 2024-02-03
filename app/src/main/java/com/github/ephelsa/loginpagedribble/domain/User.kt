package com.github.ephelsa.loginpagedribble.domain

/**
 * User model
 */
data class User(
    val name: String,
    val phone: Long,
    val email: String,
    val password: String,
)
