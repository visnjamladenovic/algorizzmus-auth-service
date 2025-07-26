package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import com.vim.algorizzmusauthservice.service.mapper.toUserDTO
import com.vim.algorizzmusauthservice.service.mapper.toUserEntity
import com.vim.algorizzmusauthservice.service.model.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val emailVerificationCodeService: EmailVerificationCodeService,
) : UserDetailsService {
    fun registerUser(user: UserDTO): UserDTO {
        if (repository.existsByUsername(user.username)) {
            throw UserAlreadyExistsException("User ${user.username} already exists")
        }

        if (repository.existsByEmail(user.email)) {
            throw UserAlreadyExistsException("User ${user.email} already exists")
        }

        val savedUser = repository.saveUser(user.toUserEntity())
        emailVerificationCodeService.sendVerificationCode(savedUser)
        return savedUser.toUserDTO()
    }

    fun verifyUserByCode(code: String) {
        val userCode = emailVerificationCodeService.fetchAndVerifyCode(code)
        verifyUser(userCode.user)
        emailVerificationCodeService.deleteById(userCode.id)
    }

    override fun loadUserByUsername(username: String): UserDTO {
        return repository.getUserByUsername(username).get().toUserDTO()
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
        val userCode = emailVerificationCodeService.fetchAndVerifyCode(code)
        val user = userCode.user
        resetUserPassword(user, newPassword)

        emailVerificationCodeService.deleteById(userCode.id)
    }

    private fun resetUserPassword(
        user: UserEntity,
        newPassword: String,
    ) {
        user.password = newPassword
        repository.saveUser(user)
    }

    private fun verifyUser(user: UserEntity) {
        user.isVerified = true
        repository.saveUser(user)
    }
}
