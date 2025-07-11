package com.vim.algorizzmusauthservice.service.mapper

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.enums.UserRole
import com.vim.algorizzmusauthservice.service.model.UserDTO

fun UserEntity.toUser(): UserDTO {
    return UserDTO(
        userUsername = this.username,
        userPassword = this.password,
        userRole = UserRole.ALGO_ADMIN,
    )
}
