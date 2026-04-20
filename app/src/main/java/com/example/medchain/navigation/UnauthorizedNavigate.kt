package com.example.speediz.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.medchain.Feature.unauthorized.screenOnBoard
import com.example.medchain.Feature.unauthorized.signInSuccess.screenSuccess
import com.example.medchain.Feature.unauthorized.signin.screenSignIn

fun NavGraphBuilder.unauthorizedNavigate(
    navController: NavHostController
) {
    screenOnBoard(
        onNavigateToSignIn = {
            navController.navigate(UnauthorizedRoute.SignIn.route)
        }
    )
    screenSignIn(
        onBackPress = {
            navController.popBackStack()
        },
        onNavigateSuccess = {
            navController.navigate(UnauthorizedRoute.Success.route)
        }
    )
    screenSuccess(
        onBackPress = {
            navController.popBackStack()
        },
        onNavigateTo = {
            navController.navigate(AuthorizedRoute.Home.route)
        }
    )
}