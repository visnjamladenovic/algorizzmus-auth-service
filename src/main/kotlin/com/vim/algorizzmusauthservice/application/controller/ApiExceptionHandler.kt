package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.response.ApiErrorResponse
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotVerifiedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(e: UserAlreadyExistsException): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity(ApiErrorResponse(HttpStatus.CONFLICT, e.message), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(UserNotVerifiedException::class)
    fun handleUserNotVerifiedException(e: UserNotVerifiedException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity(
            ApiErrorResponse(HttpStatus.FORBIDDEN, e.message),
            HttpStatus.FORBIDDEN,
        )
}
