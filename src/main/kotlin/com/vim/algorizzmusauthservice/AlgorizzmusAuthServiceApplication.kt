package com.vim.algorizzmusauthservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class AlgorizzmusAuthServiceApplication

fun main(args: Array<String>) {
    runApplication<AlgorizzmusAuthServiceApplication>(*args)
}
