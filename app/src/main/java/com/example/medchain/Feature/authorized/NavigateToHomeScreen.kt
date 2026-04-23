package com.example.medchain.Feature.authorized

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.speediz.ui.navigation.AuthorizedRoute

fun NavController.navigateToHomeScreen(
    navOptions: androidx.navigation.NavOptions? = null
) {
    this.navigate(AuthorizedRoute.Home.route, navOptions)
}

fun resetBackStackToHomeScreen(
    navController: NavController,
) {
    navController.popBackStack(AuthorizedRoute.Home.route, false)
}

fun NavGraphBuilder.screenHome(
    onNavigateTo:(route: String) -> Unit,
) {
    composable(AuthorizedRoute.Home.route){
        ScreenHome(
            navigateTo = { route ->
                onNavigateTo(route)
            },
        )
    }
}