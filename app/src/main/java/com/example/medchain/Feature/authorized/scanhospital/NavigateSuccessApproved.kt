package com.example.medchain.Feature.authorized.scanhospital

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.speediz.ui.navigation.AuthorizedRoute

fun NavController.navigateToSuccessApproved(){
    navigate(AuthorizedRoute.successApproved.route) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.screenSuccessApproved(
    onBackPress: () -> Unit,
) {
    composable(AuthorizedRoute.successApproved.route) {
        ScreenApprovalSuccess(
            onBackHome = onBackPress,
        )
    }
}