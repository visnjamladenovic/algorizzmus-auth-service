package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.EmailVerificationTokenRepository
import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class EmailVerificationTokenService(
    private val userService: UserService,
    private val tokenRepository: EmailVerificationTokenRepository,
    private val emailService: EmailService,
    private val userRepository: UserRepository,
) {
    fun generateAndSendCode(email: String) {
        val user = userRepository.findUserByEmail(email)
        if (user.isEmpty) throw UserNotFoundException("User with email $email not found")
        val codeEntity =
            EmailVerificationTokenEntity(
                user = user.get(),
            )
        tokenRepository.saveCode(codeEntity)
        emailService.sendVerificationEmail(
            email,
            codeEntity.code,
            username = user.get().username,
        )
    }
}
