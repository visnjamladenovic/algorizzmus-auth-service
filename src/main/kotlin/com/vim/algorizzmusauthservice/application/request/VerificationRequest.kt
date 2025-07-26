package com.vim.algorizzmusauthservice.application.request

import jakarta.validation.constraints.NotBlank

data class VerificationRequest(
    @field:NotBlank(message = "Code cannot be blank")
    val code: String = "",
)
