package com.example.medchain.Feature.unauthorized.signin

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.medchain.core.data.ConfirmInfo
import com.example.speediz.ui.navigation.UnauthorizedRoute

fun NavController.navigateToSignIn(
    confirmInfo: ConfirmInfo? = null,
){
    val bundle = Bundle().apply {
        putString("token", confirmInfo?.token)
    }

    navigate(UnauthorizedRoute.SignIn.route) {
        launchSingleTop = true
    }

    getBackStackEntry(UnauthorizedRoute.SignIn.route)
        .savedStateHandle["signInBundle"] = bundle
}
fun NavGraphBuilder.screenSignIn(
    onBackPress: () -> Unit,
    onNavigateSuccess: () -> Unit,
){
    composable(UnauthorizedRoute.SignIn.route) {
        val token = it.savedStateHandle.get<Bundle>("signInBundle")?.getString("token")
        ScreenSignIn(
            onBackPress = onBackPress,
            onNavigateTo = onNavigateSuccess,
            scannedToken = token,
        )
    }
}