package com.example.speediz.ui.navigation


sealed class AuthorizedRoute(val route : String) {
    data object Home : AuthorizedRoute(route = "home")
    data object Profile : AuthorizedRoute(route = "profile")
    data object scanQrAccess: AuthorizedRoute(route = "scan_qr_access")
}