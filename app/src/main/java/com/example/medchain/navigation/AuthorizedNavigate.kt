package com.example.speediz.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.medchain.Feature.authorized.navigateToHomeScreen
import com.example.medchain.Feature.authorized.scanhospital.screenScanHospitalQr
import com.example.medchain.Feature.authorized.scanhospital.screenSuccessApproved
import com.example.medchain.Feature.authorized.screenHome
import com.example.medchain.Feature.unauthorized.signin.navigateToSignIn

fun NavGraphBuilder.authorizedNavigate(
    navController: NavController
){
    screenHome(
        onNavigateTo = { route ->
            navController.navigate(route)
        }
    )
    screenScanHospitalQr(
        onBackPress = {
            navController.popBackStack()
        },
        navigateTo = { confirmInfo ->
            navController.navigate(AuthorizedRoute.successApproved.route)
        }
    )
    screenSuccessApproved(
        onBackPress = {
          navController.navigateToHomeScreen()
        },
    )
}
