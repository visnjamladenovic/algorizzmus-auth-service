package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.application.response.ApiErrorResponse
import com.vim.algorizzmusauthservice.datasource.exception.DatasourceException
import com.vim.algorizzmusauthservice.service.exception.CodeAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.CodeExpiredException
import com.vim.algorizzmusauthservice.service.exception.CodeNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserAlreadyExistsException
import com.vim.algorizzmusauthservice.service.exception.UserNotFoundException
import com.vim.algorizzmusauthservice.service.exception.UserNotVerifiedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(e: UserNotFoundException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity(
            ApiErrorResponse(HttpStatus.NOT_FOUND, e.message),
            HttpStatus.NOT_FOUND,
        )

    @ExceptionHandler(CodeNotFoundException::class)
    fun handleCodeNotFoundException(e: CodeNotFoundException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity(
            ApiErrorResponse(HttpStatus.NOT_FOUND, e.message),
            HttpStatus.NOT_FOUND,
        )

    @ExceptionHandler(CodeExpiredException::class)
    fun handleCodeExpiredException(e: CodeExpiredException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity(
            ApiErrorResponse(HttpStatus.FORBIDDEN, e.message),
            HttpStatus.FORBIDDEN,
        )

    @ExceptionHandler(CodeAlreadyExistsException::class)
    fun handleCodeAlreadyExistsException(e: CodeAlreadyExistsException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity(
            ApiErrorResponse(HttpStatus.CONFLICT, e.message),
            HttpStatus.CONFLICT,
        )

    @ExceptionHandler(DatasourceException::class)
    fun handleDatasourceException(e: DatasourceException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity(
            ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.message),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid") }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
