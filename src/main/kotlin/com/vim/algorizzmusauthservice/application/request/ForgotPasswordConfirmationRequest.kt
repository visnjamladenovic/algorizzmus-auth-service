package com.vim.algorizzmusauthservice.application.request

data class ForgotPasswordConfirmationRequest(
    val code: String,
    val newPassword: String,
)
