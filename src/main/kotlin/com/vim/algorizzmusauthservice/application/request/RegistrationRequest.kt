package com.vim.algorizzmusauthservice.application.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class RegistrationRequest(
    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "Email must be in a valid format")
    val email: String = "",
    @field:NotBlank(message = "Username cannot be blank")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9_]{3,20}$",
        message = "Username must be 3-20 characters long and contain only letters, numbers, and underscores",
    )
    val username: String = "",
    @field:NotBlank(message = "Password cannot be blank")
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$",
        message =
            "Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase" +
                "letter, one digit, and one special character (@\$!%*?&)",
    )
    val password: String = "",
)
