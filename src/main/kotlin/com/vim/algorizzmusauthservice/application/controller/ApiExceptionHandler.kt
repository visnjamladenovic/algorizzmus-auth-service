package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.model.ApiErrorResponse
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
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
}
