package com.vim.algorizzmusauthservice.application.response

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
)
