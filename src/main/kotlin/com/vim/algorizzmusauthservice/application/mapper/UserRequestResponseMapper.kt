package com.vim.algorizzmusauthservice.application.mapper

import com.vim.algorizzmusauthservice.application.request.RegistrationRequest
import com.vim.algorizzmusauthservice.application.response.UserResponse
import com.vim.algorizzmusauthservice.service.enums.UserRole
import com.vim.algorizzmusauthservice.service.model.UserDTO

fun UserDTO.toUserResponse(): UserResponse {
    return UserResponse(
        username = this.username,
        email = this.email,
        isVerified = this.isVerified,
        role = this.role,
    )
}

fun RegistrationRequest.toUserDTO(encodedPassword: String): UserDTO {
    return UserDTO(
        username = this.username,
        password = encodedPassword,
        email = this.email,
        isVerified = false,
        role = UserRole.ALGO_USER,
    )
}
