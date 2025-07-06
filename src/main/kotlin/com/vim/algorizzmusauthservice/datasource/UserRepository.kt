package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import java.util.Optional

interface UserRepository {
    fun getUserById(id: Long): Optional<UserEntity>

    fun saveUser(userEntity: UserEntity): UserEntity

    fun findUserByUsername(username: String): Optional<UserEntity>

    fun findUserByUsernameAndPassword(
        username: String,
        password: String,
    ): Optional<UserEntity>

    fun deleteUserById(id: Long)
}
