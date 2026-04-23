package com.example.medchain.Feature.authorized.scanhospital

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.medchain.core.data.ConfirmInfo
import com.example.speediz.ui.navigation.AuthorizedRoute

fun NavGraphBuilder.screenScanHospitalQr(
    onBackPress: () -> Unit,
    navigateTo: (string: String) -> Unit = {},
){
    composable(AuthorizedRoute.scanHospitalQr.route) {
        ScreenScanHospitalQr(
            onBackPress = onBackPress,
            navigateTo = {
                navigateTo(it)
            }
        )
    }
}