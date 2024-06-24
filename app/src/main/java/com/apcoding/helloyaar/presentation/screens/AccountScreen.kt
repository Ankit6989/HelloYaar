package com.apcoding.helloyaar.presentation.screens

import android.app.Activity
import android.graphics.Bitmap
import android.service.autofill.UserData
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController

@Composable
fun AccountScreen(
    navController: NavController,
    userData: UserData,
    userViewModel: UserViewModel,
    storageViewModel: StorageViewModel
) {

    var imageDialogState by remember { mutableStateOf(false) }
    val isMediaLoading = storageViewModel.isMediaLoading.value
    val focus = LocalFocusManager.current
    var userImage by remember {
        mutableStateOf(
            userData.image ?: "https://i.hizliresim.com/x7e0wpo.png"
        )
    }

    var bitmap: Bitmap? by remember { mutableStateOf(null) }
    val context = LocalContext.current as Activity

    LaunchedEffect(key1 = true) {
        println("accountUserID: ${userData.userId}")
    }
}