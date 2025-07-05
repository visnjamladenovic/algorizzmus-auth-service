package com.vim.algorizzmusauthservice.datasource

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import java.util.Optional

interface UserRepository {
    fun getUserById(id: Long): Optional<UserEntity>
}