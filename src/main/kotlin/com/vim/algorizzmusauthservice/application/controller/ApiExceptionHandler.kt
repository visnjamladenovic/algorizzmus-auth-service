package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.model.ApiException
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserUnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<ApiException> {
        return ResponseEntity(
            ApiException(HttpStatus.NOT_FOUND, 404, exception.message),
            HttpStatus.NOT_FOUND,
        )
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(exception: UserAlreadyExistsException): ResponseEntity<ApiException> {
        return ResponseEntity(
            ApiException(HttpStatus.CONFLICT, 409, exception.message),
            HttpStatus.CONFLICT,
        )
    }

    @ExceptionHandler(UserUnauthorizedException::class)
    fun handleUserUnauthorizedException(exception: UserUnauthorizedException): ResponseEntity<ApiException> =
        ResponseEntity(
            ApiException(HttpStatus.UNAUTHORIZED, 401, exception.message),
            HttpStatus.UNAUTHORIZED,
        )
}
