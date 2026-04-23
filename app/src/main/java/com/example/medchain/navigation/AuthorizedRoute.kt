package com.example.speediz.ui.navigation


sealed class AuthorizedRoute(val route : String) {
    data object Home : AuthorizedRoute(route = "home")
    data object Profile : AuthorizedRoute(route = "profile")
    data object scanHospitalQr: AuthorizedRoute(route = "scan_hospital_qr")
    data object History : AuthorizedRoute(route = "history")
    data object Account : AuthorizedRoute(route = "account")

    data object successApproved: AuthorizedRoute(route = "success_approved")

}