package com.vim.algorizzmusauthservice.application.controller

import com.vim.algorizzmusauthservice.datasource.database.entity.UserEntity
import com.vim.algorizzmusauthservice.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {
    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(
            userService.getUserById(id),
        )
    }

    @PostMapping
    fun saveUser(
        @RequestBody userEntity: UserEntity,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(
            userService.saveUser(userEntity),
        )
    }

    @PostMapping("/login")
    fun loginUser(
        @RequestBody userEntity: UserEntity,
    ): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(
            userService.loginUser(userEntity.username, userEntity.password),
        )
    }

    @DeleteMapping("/{username}")
    fun deleteUser(
        @PathVariable username: String,
    ): ResponseEntity<Void> {
        userService.deleUser(username)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
