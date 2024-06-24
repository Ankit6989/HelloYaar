package com.apcoding.helloyaar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.apcoding.helloyaar.ui.theme.HelloYaarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
    }
}

@Composable
fun Navigation(
    darkTheme: Boolean,
    onThemeUpdated: (Boolean) -> Unit
){
    val navController = rememberAnimatedNavController()
    val userViewModel = hiltViewModel<UserViewModel>()
    val storageViewModel = hiltViewModel<StorageViewModel>()
}