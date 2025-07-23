package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.EmailVerificationCodeRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.datasource.emailservice.client.EmailClient
import com.vim.algorizzmusauthservice.datasource.emailservice.request.EmailCodeRequest
import org.springframework.stereotype.Service

@Service
class EmailVerificationCodeService(
    private val codeRepository: EmailVerificationCodeRepository,
    private val emailClient: EmailClient,
) {
    fun generateAndSendToken(user: UserEntity) {
        val codeEntity =
            EmailVerificationCodeEntity(
                user = user,
            )
        codeRepository.saveCode(codeEntity)
        emailClient.sendVerificationEmail(
            EmailCodeRequest(user.email, codeEntity.code, user.username),
        )
    }
}
