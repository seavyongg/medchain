package com.example.medchain.graphs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.medchain.Feature.unauthorized.signin.SignInViewModel
import com.example.medchain.ScreenNoInternet
import com.example.medchain.core.network.interceptor.NetworkConnectionInterceptor
import com.example.speediz.ui.navigation.AuthorizedRoute
import com.example.speediz.ui.navigation.UnauthorizedRoute
import com.example.speediz.ui.navigation.unauthorizedNavigate


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavigation(
    navController : NavHostController ,
    modifier : Modifier = Modifier ,
) {
    val signInViewModel = hiltViewModel<SignInViewModel>()
    val isLoggedIn = signInViewModel.isLoggedIn.collectAsState().value
    val isConnected = NetworkConnectionInterceptor(navController.context)
    val startDestination = if (!isLoggedIn) {
        UnauthorizedRoute.Onboarding.route
    }else {
        AuthorizedRoute.Home.route
    }
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this@SharedTransitionLayout,
        )
        {
            if (!isConnected.isConnected() ) {
                ScreenNoInternet()
            } else {
                NavHost(
                    navController = navController,
                    startDestination = startDestination,
                    modifier = modifier,
                ) {
                    unauthorizedNavigate(
                        navController = navController
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
