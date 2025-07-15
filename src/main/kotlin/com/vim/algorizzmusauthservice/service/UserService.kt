package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.mapper.toUser
import com.vim.algorizzmusauthservice.service.model.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val repository: UserRepository,
) : UserDetailsService {
    fun getUserById(id: Long): Optional<UserEntity> {
        return repository.getUserById(id)
    }

    fun registerUser(user: UserEntity): UserEntity {
        if (repository.existsByUsername(user.username)) {
            throw UserAlreadyExistsException("User ${user.username} already exists")
        }
        return repository.saveUser(user)
    }

    override fun loadUserByUsername(username: String): UserDTO {
        return repository.getUserByUsername(username).get().toUser()
    }
}
