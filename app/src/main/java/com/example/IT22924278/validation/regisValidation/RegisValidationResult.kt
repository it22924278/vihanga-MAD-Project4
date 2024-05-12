package com.example.IT22924278.validation.regisValidation

sealed interface RegisValidationResult {
    data class Empty(val errorMessage: String) : RegisValidationResult
    data class Invalid(val errorMessage: String) : RegisValidationResult
    object Valid : RegisValidationResult
}