package com.vim.algorizzmusauthservice.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val mailSender: JavaMailSender) {
    fun sendVerificationEmail(
        to: String,
        token: String,
    ) {
        val message = SimpleMailMessage()
        message.setTo(to)
        message.setSubject("Verify your email")
        message.setText("Click to verify: http://localhost:8080/verify?token=$token")
        mailSender.send(message)
    }
}
