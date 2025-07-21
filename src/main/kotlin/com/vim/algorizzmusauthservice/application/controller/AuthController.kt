package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.request.EmailVerificationRequest
import com.vim.algorizzmusauthservice.service.EmailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val emailService: EmailService) {
    @PostMapping("/verify_email")
    fun sendVerificationEmail(@RequestBody request: EmailVerificationRequest): ResponseEntity<String> {
        emailService.sendVerificationEmail(request.email, token = "dummy token")
        return ResponseEntity.ok("Verification email sent successfully.")
    }
}
