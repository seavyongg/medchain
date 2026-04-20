package com.example.medchain.Feature.unauthorized.scan

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.medchain.core.data.ConfirmInfo
import com.example.medchain.core.data.ConfirmInfoRequest
import com.example.speediz.ui.navigation.UnauthorizedRoute

fun NavController.navigateToScanAuth() {
    this.navigate(UnauthorizedRoute.ScanAuth.route)
}

fun NavGraphBuilder.screenScanAuth(
    onBackPress: () -> Unit,
    navigateTo: (confirmInfo: ConfirmInfo) -> Unit = {}
) {
    composable(UnauthorizedRoute.ScanAuth.route){
            ScreenScanAuth(
                onBackPress = onBackPress,
                navigateTo = navigateTo
            )
    }
}