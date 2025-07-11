package com.vim.algorizzmusauthservice.application.response

import org.springframework.http.HttpStatus

data class ApiErrorResponse(
    val status: HttpStatus,
    val message: String,
)
