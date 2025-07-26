package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.exception.CodeExpiredException
import com.vim.algorizzmusauthservice.service.exception.CodeNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import com.vim.algorizzmusauthservice.service.mapper.toUser
import com.vim.algorizzmusauthservice.service.model.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
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

    // da se doda limit na broj reset linkova

    fun resetUserPasswordByCode(
        code: String,
        newPassword: String,
    ) {
        val userCode = emailVerificationCodeService.findByCode(code)
        if (userCode.isEmpty) {
            throw CodeNotFoundException("Code $code not found")
        }

        if (userCode.get().expirationDate.isBefore(LocalDateTime.now())) {
            throw CodeExpiredException("Code $code expired")
        }

        val user = userCode.get().user
        resetUserPassword(user, newPassword)

        emailVerificationCodeService.deleteById(userCode.get().id)
    }

    private fun resetUserPassword(
        user: UserEntity,
        newPassword: String,
    ) {
        user.password = newPassword
        repository.saveUser(user)
    }
}
