package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import java.util.Optional

interface UserRepository {
    fun saveUser(user: UserEntity): UserEntity

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun getUserByUsername(username: String): Optional<UserEntity>

    fun findUserByEmail(email: String): Optional<UserEntity>
}
