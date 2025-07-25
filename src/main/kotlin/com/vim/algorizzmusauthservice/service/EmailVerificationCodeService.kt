package com.vim.algorizzmusauthservice.service

import com.vim.algorizzmusauthservice.datasource.EmailVerificationCodeRepository
import com.vim.algorizzmusauthservice.datasource.database.entity.EmailVerificationCodeEntity
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.datasource.emailservice.client.EmailClient
import com.vim.algorizzmusauthservice.datasource.emailservice.request.EmailCodeRequest
import com.vim.algorizzmusauthservice.service.enums.VerificationCodeType
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class EmailVerificationCodeService(
    private val codeRepository: EmailVerificationCodeRepository,
    private val emailClient: EmailClient,
) {
    fun findByCode(code: String): Optional<EmailVerificationCodeEntity> {
        return codeRepository.findByCode(code)
    }

    fun sendVerificationCode(user: UserEntity) {
        val code = generateCode(user, VerificationCodeType.REGISTRATION_CODE).code
        emailClient.sendVerificationEmail(
            EmailCodeRequest(user.email, code, user.username),
        )
    }

    fun sendForgotPasswordCode(user: UserEntity) {
        val code = generateCode(user, VerificationCodeType.PASSWORD_RESET_CODE).code
        emailClient.sendForgotPasswordEmail(
            EmailCodeRequest(user.email, code, user.username),
        )
    }

    private fun generateCode(
        user: UserEntity,
        verificationCodeType: VerificationCodeType,
    ): EmailVerificationCodeEntity {
        val codeEntity =
            EmailVerificationCodeEntity(
                user = user,
                codeType = verificationCodeType,
            )
        return codeRepository.saveCode(codeEntity)
    }
}
