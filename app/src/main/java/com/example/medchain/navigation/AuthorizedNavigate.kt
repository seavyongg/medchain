package com.example.speediz.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.medchain.Feature.authorized.screenHome

fun NavGraphBuilder.authorizedNavigate(
    navController: NavController
){
    screenHome(
        onNavigateTo = { route ->
            navController.navigate(route)
        }
    )
}
