package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.EmailVerificationTokenRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationTokenEntity
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity

class EmailVerificationTokenService(
    private val repository: EmailVerificationTokenRepository,
    val emailService: EmailService,
) {
    fun generateAndSendToken(user: UserEntity) {
        val tokenEntity = EmailVerificationTokenEntity(user)
        repository.saveToken(tokenEntity)
        emailService.sendVerificationEmail(user.email, tokenEntity.token)
    }
}
