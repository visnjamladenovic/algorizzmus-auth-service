package com.vim.algorizzmusauthservice.service.mapper

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.enums.UserRole
import com.vim.algorizzmusauthservice.service.model.UserDTO

fun UserEntity.toUserDTO(): UserDTO {
    return UserDTO(
        username = this.username,
        password = this.password,
        role = UserRole.ALGO_ADMIN,
        email = this.email,
        isVerified = this.isVerified,
    )
}

fun UserDTO.toUserEntity(): UserEntity {
    val userEntity = UserEntity()
    userEntity.username = this.username
    userEntity.password = this.password
    userEntity.email = this.email
    userEntity.isVerified = this.isVerified
    return userEntity
}
