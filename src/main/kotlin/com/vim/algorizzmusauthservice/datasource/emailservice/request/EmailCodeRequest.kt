package com.vim.algorizzmusauthservice.datasource.emailservice.request

data class EmailCodeRequest(
    val email: String,
    val code: String,
)
