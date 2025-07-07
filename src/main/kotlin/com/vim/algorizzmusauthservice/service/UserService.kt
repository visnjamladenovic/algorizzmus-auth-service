package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(private val repository: UserRepository) {
    fun getUserById(id: Long): Optional<UserEntity> {
        return repository.getUserById(id)
    }

    fun registerUser(user: UserEntity): UserEntity {
        if (repository.existsByUsername(user.username)) {
            throw UserAlreadyExistsException("User ${user.username} already exists")
        }
        return repository.saveUser(user)
    }
}
