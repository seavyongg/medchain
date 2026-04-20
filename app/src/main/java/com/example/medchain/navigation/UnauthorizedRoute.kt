package com.example.speediz.ui.navigation

sealed class UnauthorizedRoute(val route : String) {
    data object Onboarding : UnauthorizedRoute(route = "onboarding")
    data object SignIn : UnauthorizedRoute(route = "sign_in")
    data object Success: UnauthorizedRoute(route = "success")
}