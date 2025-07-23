package com.vim.algorizzmusauthservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AlgorizzmusAuthServiceApplication

fun main(args: Array<String>) {
    runApplication<AlgorizzmusAuthServiceApplication>(*args)
}
