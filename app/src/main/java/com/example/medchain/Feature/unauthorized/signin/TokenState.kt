package com.example.medchain.Feature.unauthorized.signin

sealed class TokenState {
    object Idle : TokenState()
    object Loading : TokenState()
    data class Success(val token: String) : TokenState()
    data class Error(val message: String) : TokenState()
}