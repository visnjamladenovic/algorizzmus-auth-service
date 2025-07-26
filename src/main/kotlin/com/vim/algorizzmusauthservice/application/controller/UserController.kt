package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.annotation.StandardErrorResponses
import com.vim.algorizzmusauthservice.application.request.AuthenticationRequest
import com.vim.algorizzmusauthservice.application.request.ForgotPasswordConfirmationRequest
import com.vim.algorizzmusauthservice.application.request.ForgotPasswordEmailRequest
import com.vim.algorizzmusauthservice.application.request.RegistrationRequest
import com.vim.algorizzmusauthservice.application.request.VerificationRequest
import com.vim.algorizzmusauthservice.application.response.AuthResponse
import com.vim.algorizzmusauthservice.application.response.UserResponse
import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "User Controller", description = "Register new or login as existing user")
interface UserController {
    @Operation(
        summary = "Login",
        description = "Login as an existing user",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User successfully logged in",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema =
                            Schema(
                                implementation = AuthResponse::class,
                                example = TOKEN_EXAMPLE,
                            ),
                    ),
                ],
            ),
        ],
    )
    @StandardErrorResponses
    fun loginUser(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User Request Object",
            required = true,
            content = [
                Content(
                    schema = Schema(implementation = AuthenticationRequest::class),
                ),
            ],
        )
        authenticationRequest: AuthenticationRequest,
    ): ResponseEntity<AuthResponse>

    @Operation(
        summary = "Register",
        description = "Register new user",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User successfully registered",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema =
                            Schema(
                                implementation = UserEntity::class,
                            ),
                    ),
                ],
            ),
        ],
    )
    @SecurityRequirement(name = "bearerAuth")
    @StandardErrorResponses
    fun registerUser(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User Request Object",
            required = true,
            content = [
                Content(
                    schema = Schema(implementation = RegistrationRequest::class),
                ),
            ],
        )
        @RequestBody registrationRequest: RegistrationRequest,
    ): ResponseEntity<UserResponse>

    fun verifyUser(
        @RequestBody verificationRequest: VerificationRequest,
    ): ResponseEntity<Void>

    fun forgotPasswordEmail(
        @RequestBody forgotPasswordEmailRequest: ForgotPasswordEmailRequest,
    ): ResponseEntity<Void>

    fun forgotPasswordConfirmation(
        @RequestBody forgotPasswordConfirmationRequest: ForgotPasswordConfirmationRequest,
    ): ResponseEntity<Void>

    companion object {
        private const val TOKEN_EXAMPLE =
            "{\"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbGlqYSIsImlhdCI6MTc0NDYzODU3MywiZXhwIjoxNzQ0NjQyMTc" +
                "zfQ.MS65GTeSFCUBteva3EfvkwxOY-2eoHmKPGU9ozXcUa0\"}"
    }
}
