package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.enums.TokenType
import com.vim.algorizzmusauthservice.application.mapper.toUserDTO
import com.vim.algorizzmusauthservice.application.mapper.toUserResponse
import com.vim.algorizzmusauthservice.application.request.AuthenticationRequest
import com.vim.algorizzmusauthservice.application.request.ForgotPasswordConfirmationRequest
import com.vim.algorizzmusauthservice.application.request.ForgotPasswordEmailRequest
import com.vim.algorizzmusauthservice.application.request.RegistrationRequest
import com.vim.algorizzmusauthservice.application.request.VerificationRequest
import com.vim.algorizzmusauthservice.application.response.AuthResponse
import com.vim.algorizzmusauthservice.application.response.UserResponse
import com.vim.algorizzmusauthservice.application.security.JwtGenerator
import com.vim.algorizzmusauthservice.service.UserService
import com.vim.algorizzmusauthservice.service.exception.UserNotVerifiedException
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserRestController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtGenerator: JwtGenerator,
    private val passwordEncoder: PasswordEncoder,
) : UserController {
    @PostMapping("/register")
    override fun registerUser(
        @RequestBody @Valid registrationRequest: RegistrationRequest,
    ): ResponseEntity<UserResponse> {
        val password = passwordEncoder.encode(registrationRequest.password)
        val createdUser = userService.registerUser(registrationRequest.toUserDTO(password))
        return ResponseEntity.ok(createdUser.toUserResponse())
    }

    @PostMapping("/verify")
    override fun verifyUser(
        @RequestBody @Valid verificationRequest: VerificationRequest,
    ): ResponseEntity<Void> {
        userService.verifyUserByCode(verificationRequest.code)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    override fun loginUser(
        @RequestBody @Valid authenticationRequest: AuthenticationRequest,
    ): ResponseEntity<AuthResponse> {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password,
            )
        val authentication = authenticationManager.authenticate(authenticationToken)
        val accessToken = jwtGenerator.generateToken(authentication, TokenType.ACCESS)
        val refreshToken = jwtGenerator.generateToken(authentication, TokenType.REFRESH)
        val user = userService.loadUserByUsername(authenticationRequest.username)
        if (!user.isVerified) throw UserNotVerifiedException("User ${user.username} not verified")

        return ResponseEntity.ok(AuthResponse(accessToken, refreshToken))
    }

    @PostMapping("/forgot-password-email")
    override fun forgotPasswordEmail(
        @RequestBody forgotPasswordEmailRequest: ForgotPasswordEmailRequest,
    ): ResponseEntity<Void> {
        userService.forgotPasswordEmail(forgotPasswordEmailRequest.email)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/forgot-password-confirmation")
    override fun forgotPasswordConfirmation(forgotPasswordConfirmationRequest: ForgotPasswordConfirmationRequest): ResponseEntity<Void> {
        val password = passwordEncoder.encode(forgotPasswordConfirmationRequest.newPassword)
        userService.resetUserPasswordByCode(forgotPasswordConfirmationRequest.code, password)
        return ResponseEntity.noContent().build()
    }
}
