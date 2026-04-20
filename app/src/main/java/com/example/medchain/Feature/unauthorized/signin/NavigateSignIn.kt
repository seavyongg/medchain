package com.example.medchain.Feature.unauthorized.signin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.speediz.ui.navigation.UnauthorizedRoute

fun NavGraphBuilder.screenSignIn(
    onBackPress: () -> Unit,
    onNavigateSuccess: () -> Unit,
){
    composable(UnauthorizedRoute.SignIn.route) {
        ScreenSignIn(
            onBackPress = onBackPress,
            onNavigateTo = onNavigateSuccess,
        )
    }
}