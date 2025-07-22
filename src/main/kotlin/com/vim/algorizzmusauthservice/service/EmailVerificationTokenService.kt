package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.EmailVerificationTokenRepository
import com.vim.algorizzmusauthservice.datasource.UserRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EmailVerificationTokenService(
    private val userService: UserService,
    private val tokenRepository: EmailVerificationTokenRepository,
    private val emailService: EmailService,
    private val userRepository: UserRepository,
) {
    fun generateAndSendToken(email: String) {
        val user =
            userRepository.findUserByEmail(email)
                ?: throw UserNotFoundException("User with email $email not found")
        val token = UUID.randomUUID().toString()
        val tokenEntity =
            EmailVerificationTokenEntity(
                token = token,
                user = user,
            )
        tokenRepository.saveToken(tokenEntity)
        emailService.sendVerificationEmail(
            email,
            token,
        )
    }
}
