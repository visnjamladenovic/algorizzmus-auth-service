package com.vim.algorizzmusauthservice.application.response

import com.vim.algorizzmusauthservice.service.enums.UserRole

data class UserResponse(
    val username: String,
    val email: String,
    val isVerified: Boolean,
    val role: UserRole,
)
