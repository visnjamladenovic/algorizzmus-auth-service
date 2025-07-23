package com.vim.algorizzmusauthservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class EmailService(
    private val mailSender: JavaMailSender,
    private val templateEngine: TemplateEngine? = null,
) {
    @Value("\${spring.mail.username}")
    private lateinit var mailUsername: String

    private fun sendMail(
        to: String,
        subject: String,
        text: String,
    ) {
        val message = SimpleMailMessage()
        message.setTo(to)
        message.subject = subject
        message.text = text
        mailSender.send(message)
    }

    private fun sendHtmlMail(
        to: String,
        subject: String,
        htmlContent: String,
        from: String = mailUsername,
    ) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)
        helper.setFrom(from)
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)
        mailSender.send(message)
    }

    private fun processTemplate(
        templateName: String,
        variables: Map<String, Any>,
    ): String? {
        return templateEngine?.let {
            val context = Context()
            variables.forEach { (key, value) -> context.setVariable(key, value) }
            it.process(templateName, context)
        }
    }

    fun sendVerificationEmail(
        to: String,
        code: String,
        username: String? = null,
    ) {
        val htmlContent =
            processTemplate(
                "email/registration-confirmation",
                mapOf(
                    "username" to (username ?: "User"),
                    "code" to code,
                ),
            )

        if (htmlContent != null) {
            sendHtmlMail(to, REGISTRATION_EMAIL_SUBJECT, htmlContent)
        } else {
            val body = "Your verification code is $code"
            sendMail(to, REGISTRATION_EMAIL_SUBJECT, body)
        }
    }

    fun sendVerificationEmail(
        to: String,
        code: String,
    ) {
        val body = "Your verification code is $code"
        sendMail(to, REGISTRATION_EMAIL_SUBJECT, body)
    }

    fun sendPasswordResetEmail(
        to: String,
        code: String,
    ) {
        val body = "Your password reset code is $code"
        sendMail(to, PASSWORD_RESET_EMAIL_SUBJECT, body)
    }

    companion object {
        private const val REGISTRATION_EMAIL_SUBJECT = "Verify your email"
        private const val PASSWORD_RESET_EMAIL_SUBJECT = "Reset your password"
    }
}
