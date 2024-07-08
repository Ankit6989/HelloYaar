package com.apcoding.helloyaar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.apcoding.helloyaar.common.Constants.TOPIC
import com.apcoding.helloyaar.data.remote.UserData
import com.apcoding.helloyaar.data_store.StoreSettings
import com.apcoding.helloyaar.presentation.screens.*
import com.apcoding.helloyaar.presentation.screens.auth.*
import com.apcoding.helloyaar.presentation.screens.viewmodel.StorageViewModel
import com.apcoding.helloyaar.presentation.screens.viewmodel.UserViewModel
import com.apcoding.helloyaar.ui.theme.HelloYaarTheme
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.google.accompanist.navigation.animation.AnimatedNavHost

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


       // WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val context = LocalContext.current
            val dataStore = StoreSettings(context)
            var darkTheme by remember { mutableStateOf(false) }
            val savedDarkMode = dataStore.getDarkMode.collectAsState(initial = false)
            val scope = rememberCoroutineScope()

            HelloYaarTheme(darkTheme = savedDarkMode.value ?: false) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.surface
            ) {
                Navigator(
                    darkTheme = savedDarkMode.value ?: false,
                ) {
                    scope.launch {
                        darkTheme = !(savedDarkMode.value ?: false)
                        dataStore.saveDarkMode(darkTheme)
                        }
                    }
                }
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
    }
}

/*
@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigator(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val navController = rememberAnimatedNavController()
    val userViewModel = hiltViewModel<UserViewModel>()
    val storageViewModel = hiltViewModel<StorageViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("main",
           /* exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(600))
            }, enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            }*/
        ) {
            MainScreen(navController = navController, userViewModel = userViewModel)
        }
        composable(
            "message",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            val userData = navController
                .previousBackStackEntry
                ?.arguments
                ?.getParcelable<UserData>("userData")

            userData?.let {
                MessageScreen(
                    navController = navController,
                    userData = userData
                )
            }
        }
        composable(
            "sign_in",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popEnterTransition = {

                slideInHorizontally(
                    initialOffsetX = { -8000 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                )
                fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            SignInScreen(navController)
        }
        composable(
            "sign_up",
            /*enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            SignUpScreen(navController)
        }
        composable(
            "settings",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popEnterTransition = {

                slideInHorizontally(
                    initialOffsetX = { -8000 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                )
                fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            SettingsScreen(
                navController = navController,
                userViewModel = userViewModel,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable(
            "account",
            /*enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popEnterTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                )
                fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            val userData =
                navController.previousBackStackEntry?.arguments?.getParcelable<UserData>("userData")

            userData?.let {
                AccountScreen(
                    navController = navController,
                    userData = it,
                    userViewModel = userViewModel,
                    storageViewModel = storageViewModel
                )
            }
        }
        composable(
            "people",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popEnterTransition = {

                slideInHorizontally(
                    initialOffsetX = { -8000 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                )
                fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            PeopleScreen(navController)
        }
        composable(
            "auth",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popEnterTransition = {

                slideInHorizontally(
                    initialOffsetX = { -8000 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                )
                fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            AuthScreen(navController)
        }
        composable(
            "forgot_password",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            ForgotPasswordScreen()
        }
        composable(
            "splash_screen",
           /* enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeIn(animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    )
                ) +
                        fadeOut(animationSpec = tween(600))
            },*/
        ) {
            SplashScreen(navController)
        }

    }


}
*/


/*
@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigator(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val navController = rememberAnimatedNavController()
    val userViewModel = hiltViewModel<UserViewModel>()
    val storageViewModel = hiltViewModel<StorageViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("main") {
            MainScreen(navController = navController, userViewModel = userViewModel)
        }
        composable("message") {
            val userData = navController.previousBackStackEntry?.arguments?.getParcelable<UserData>("userData")
            userData?.let {
                MessageScreen(navController = navController, userData = it)
            }
        }
        composable("sign_in") {
            SignInScreen(navController)
        }
        composable("sign_up") {
            SignUpScreen(navController)
        }
        composable("settings") {
            SettingsScreen(
                navController = navController,
                userViewModel = userViewModel,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable("account") {
            val userData = navController.previousBackStackEntry?.arguments?.getParcelable<UserData>("userData")
            userData?.let {
                AccountScreen(
                    navController = navController,
                    userData = it,
                    userViewModel = userViewModel,
                    storageViewModel = storageViewModel
                )
            }
        }
        composable("people") {
            PeopleScreen(navController)
        }
        composable("auth") {
            AuthScreen(navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen()
        }
        composable("splash_screen") {
            SplashScreen(navController)
        }
    }
}
*/




@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigator(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    val navController = rememberAnimatedNavController()
    val userViewModel = hiltViewModel<UserViewModel>()
    val storageViewModel = hiltViewModel<StorageViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("main") {
            MainScreen(navController = navController, userViewModel = userViewModel)
        }
        composable("message") {
            val userData = navController.previousBackStackEntry?.arguments?.getParcelable<UserData>("userData")
            userData?.let {
                MessageScreen(navController = navController, userData = it)
            }
        }
        composable("sign_in") {
            SignInScreen(navController)
        }
        composable("sign_up") {
            SignUpScreen(navController)
        }
        composable("settings") {
            SettingsScreen(
                navController = navController,
                userViewModel = userViewModel,
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable("account") {
            val userData = navController.previousBackStackEntry?.arguments?.getParcelable<UserData>("userData")
            userData?.let {
                AccountScreen(
                    navController = navController,
                    userData = it,
                    userViewModel = userViewModel,
                    storageViewModel = storageViewModel
                )
            }
        }
        composable("people") {
            PeopleScreen(navController)
        }
        composable("auth") {
            AuthScreen(navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen()
        }
        composable("splash_screen") {
            SplashScreen(navController)
        }
    }
}
