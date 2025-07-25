package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.exception.CodeNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import com.vim.algorizzmusauthservice.service.mapper.toUser
import com.vim.algorizzmusauthservice.service.model.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val repository: UserRepository,
    private val emailVerificationCodeService: EmailVerificationCodeService,
) : UserDetailsService {
    fun getUserById(id: Long): Optional<UserEntity> {
        return repository.getUserById(id)
    }

    fun registerUser(user: UserEntity): UserEntity {
        if (repository.existsByUsername(user.username)) {
            throw UserAlreadyExistsException("User ${user.username} already exists")
        }

        val saveUser = repository.saveUser(user)
        emailVerificationCodeService.sendVerificationCode(saveUser)
        return saveUser
    }

    override fun loadUserByUsername(username: String): UserDTO {
        return repository.getUserByUsername(username).get().toUser()
    }

    fun findUserByEmail(email: String): Optional<UserEntity> {
        return repository.findUserByEmail(email)
    }

    fun forgotPasswordEmail(email: String) {
        val user = repository.findUserByEmail(email)
        if (user.isEmpty) {
            throw UserNotFoundException("User with email $email not found")
        }
        emailVerificationCodeService.sendForgotPasswordCode(user.get())
    }

    fun resetUserPasswordByCode(
        code: String,
        newPassword: String,
    ) {
        val dbCode = emailVerificationCodeService.findByCode(code)
        if (dbCode.isEmpty) {
            throw CodeNotFoundException("Code $code not found")
        }
        val dbUser = dbCode.get().user
        resetUserPassword(dbUser, newPassword)
    }

    private fun resetUserPassword(
        user: UserEntity,
        newPassword: String,
    ) {
        user.password = newPassword
        repository.saveUser(user)
    }
}
