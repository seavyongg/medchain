package com.example.medchain.Feature.unauthorized.signInSuccess

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.speediz.ui.navigation.UnauthorizedRoute

fun NavController.navigationSuccess(
    navOptions: androidx.navigation.NavOptions? = null
) {
    this.navigate(UnauthorizedRoute.Success.route, navOptions)
}
fun NavGraphBuilder.screenSuccess(
    onBackPress: () -> Unit,
    onNavigateTo: () -> Unit,
){
    composable(UnauthorizedRoute.Success.route){
        ScreenSuccess(
            onBackPress = onBackPress,
            onNavigateTo = onNavigateTo,
        )
    }
}