package com.example.medchain.graphs

import android.Manifest
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.medchain.Feature.appcompat.DynamicPermissionDialog
import com.example.medchain.Feature.unauthorized.signin.SignInViewModel
import com.example.medchain.ScreenNoInternet
import com.example.medchain.core.network.interceptor.NetworkConnectionInterceptor
import com.example.speediz.ui.navigation.AuthorizedRoute
import com.example.speediz.ui.navigation.UnauthorizedRoute
import com.example.speediz.ui.navigation.authorizedNavigate
import com.example.speediz.ui.navigation.unauthorizedNavigate
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalPermissionsApi::class)
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
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)
    var requestedOnce by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!cameraPermission.status.isGranted && !requestedOnce) {
            requestedOnce = true
            cameraPermission.launchPermissionRequest()
        }
    }

    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this@SharedTransitionLayout,
        )
        {
            when {
                !isConnected.isConnected() -> {
                    ScreenNoInternet()
                }
                cameraPermission.status.isGranted -> {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = modifier,
                    ) {
                        unauthorizedNavigate(
                            navController = navController
                        )
                        authorizedNavigate(
                            navController = navController
                        )
                    }
                }
                else -> {}
            }
        }
    }
}
@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
