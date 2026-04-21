package com.example.speediz.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.medchain.Feature.unauthorized.scan.navigateToScanAuth
import com.example.medchain.Feature.unauthorized.scan.screenScanAuth
import com.example.medchain.Feature.unauthorized.screenOnBoard
import com.example.medchain.Feature.unauthorized.signInSuccess.screenSuccess
import com.example.medchain.Feature.unauthorized.signin.navigateToSignIn
import com.example.medchain.Feature.unauthorized.signin.screenSignIn

fun NavGraphBuilder.unauthorizedNavigate(
    navController: NavHostController
) {
    screenOnBoard(
        onNavigateToSignIn = {
            navController.navigateToScanAuth()
        }
    )
    screenSignIn(
        onBackPress = {
            navController.popBackStack()
        },
        onNavigateSuccess = {
            navController.navigate(UnauthorizedRoute.Success.route)
        },
    )
    screenSuccess(
        onBackPress = {
            navController.navigate(AuthorizedRoute.Home.route) {
                popUpTo(UnauthorizedRoute.Onboarding.route) { inclusive = true }
            }
        },
        onNavigateTo = {
            //after success reset to home
            navController.navigate(AuthorizedRoute.Home.route) {
                popUpTo(UnauthorizedRoute.Onboarding.route) { inclusive = true }
            }
        }
    )
    screenScanAuth(
        onBackPress = {
            navController.popBackStack()
        },
        navigateTo = {confirmInfo ->
            navController.navigateToSignIn(confirmInfo)
        }
    )
}