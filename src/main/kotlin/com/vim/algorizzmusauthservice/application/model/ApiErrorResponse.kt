package com.vim.algorizzmusauthservice.application.model

import org.springframework.http.HttpStatus

data class ApiErrorResponse(
    var status: HttpStatus,
    var message: String,
)
