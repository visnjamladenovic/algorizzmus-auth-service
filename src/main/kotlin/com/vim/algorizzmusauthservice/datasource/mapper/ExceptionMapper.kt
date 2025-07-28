package com.vim.algorizzmusauthservice.datasource.mapper

import com.vim.algorizzmusauthservice.datasource.exception.DatasourceException

inline fun <T> safeDbCall(action: () -> T): T {
    return try {
        action()
    } catch (ex: Exception) {
        throw DatasourceException("Database operation failed: ${ex.message}")
    }
}
