package com.apcoding.helloyaar.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.apcoding.helloyaar.R
import com.apcoding.helloyaar.common.MyCheckSignedIn
import com.apcoding.helloyaar.presentation.screens.viewmodel.AuthViewModel

@Composable
fun AuthScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    MyCheckSignedIn(navController = navController, viewModel = authViewModel)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.hello_yaar_background
            ),
            contentDescription = ""
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(top = 40.dp)) {
            Text(
                text = "Hello Yaar",
                fontFamily = FontFamily.Serif,
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                text = " Connect friends \n\n easily & quickly ",
                fontFamily = FontFamily.Cursive,
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )
        }
        Column(Modifier.fillMaxWidth()) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.background)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end =30.dp), onClick = {
                navController.navigate("sign_up")
            }) {
                Text(text = "Sign up", color = Color.White)
            }
            Spacer(modifier = Modifier.size(8.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account? ", color = Color.White)
                Text(text = "Sign in", modifier = Modifier.clickable {
                    navController.navigate("sign_in")
                }, textDecoration = TextDecoration.Underline, color = Color.White)
            }
        }


    }
}