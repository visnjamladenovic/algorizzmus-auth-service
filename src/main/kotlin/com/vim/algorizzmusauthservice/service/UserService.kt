package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserUnauthorizedException
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {
    fun getUserById(id: Long): UserEntity {
        val user = repository.getUserById(id)
        if (user.isEmpty) {
            throw UserNotFoundException("User not found with id: $id")
        }
        return user.get()
    }

    fun saveUser(userEntity: UserEntity): UserEntity {
        if (repository.findUserByUsername(userEntity.username).isPresent) {
            throw UserAlreadyExistsException("User with username: ${userEntity.username} already exists.")
        }
        return repository.saveUser(userEntity)
    }

    fun loginUser(
        username: String,
        password: String,
    ): UserEntity {
        val user = repository.findUserByUsernameAndPassword(username, password)
        if (user.isEmpty) {
            throw UserUnauthorizedException("User unauthorized: $username")
        }
        return user.get()
    }

    fun deleUser(username: String) {
        val user = repository.findUserByUsername(username)
        if (user.isEmpty) {
            throw UserNotFoundException("User not found with username: $username")
        }
        repository.deleteUserById(user.get().id)
    }
}
