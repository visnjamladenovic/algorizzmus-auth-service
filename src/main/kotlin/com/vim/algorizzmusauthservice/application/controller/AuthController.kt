package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.request.EmailVerificationRequest
import com.vim.algorizzmusauthservice.service.EmailVerificationTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val emailVerificationTokenService: EmailVerificationTokenService) {
    @PostMapping("/verify_email")
    fun sendVerification(
        @RequestBody request: EmailVerificationRequest,
    ): ResponseEntity<String> {
        emailVerificationTokenService.generateAndSendCode(request.email)
        return ResponseEntity.ok("Verification email sent successfully.")
    }
}
