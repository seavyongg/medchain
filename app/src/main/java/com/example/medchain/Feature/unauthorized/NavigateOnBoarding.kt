package com.example.medchain.Feature.unauthorized

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.speediz.ui.navigation.UnauthorizedRoute

fun NavGraphBuilder.screenOnBoard(
    onNavigateToSignIn: () -> Unit,
) {
    composable(UnauthorizedRoute.Onboarding.route) {
        ScreenOnBoarding(
            onNavigateTo = onNavigateToSignIn,
        )
    }
}