package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.request.AuthenticationRequest
import com.vim.algorizzmusauthservice.application.response.AuthResponse
import com.vim.algorizzmusauthservice.application.security.JwtGenerator
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
    private val jwtGenerator: JwtGenerator,
    private val passwordEncoder: PasswordEncoder,
) {
    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(
            userService.getUserById(id).get(),
        )
    }

    @PostMapping("/register")
    fun registerUser(
        @RequestBody user: UserEntity,
    ): ResponseEntity<UserEntity> {
        user.password = passwordEncoder.encode(user.password)
        return ResponseEntity.ok(userService.registerUser(user))
    }

    @PostMapping("/login")
    fun loginUser(
        @RequestBody authenticationRequest: AuthenticationRequest,
    ): ResponseEntity<AuthResponse> {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password,
            )
        val user = authenticationManager.authenticate(authenticationToken)
        val jwtToken = jwtGenerator.generateToken(user)

        return ResponseEntity.ok(AuthResponse(jwtToken))
    }
}
