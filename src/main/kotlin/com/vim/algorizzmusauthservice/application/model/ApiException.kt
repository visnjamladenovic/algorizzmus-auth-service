package com.vim.algorizzmusauthservice.application.model

import org.springframework.http.HttpStatus

data class ApiException(
    var status: HttpStatus,
    var statusCode: Int,
    var message: String,
)
