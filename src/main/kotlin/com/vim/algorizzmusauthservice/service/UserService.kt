package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(private val repository: UserRepository) {
    fun getUserById(id: Long): Optional<UserEntity> {
        return repository.getUserById(id)
    }
}
