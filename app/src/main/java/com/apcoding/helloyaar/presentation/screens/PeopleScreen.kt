package com.apcoding.helloyaar.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.apcoding.helloyaar.presentation.components.UsersItem
import com.apcoding.helloyaar.presentation.screens.components.SearchBar
import com.apcoding.helloyaar.presentation.screens.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
) {

    val users = userViewModel.usersData.value
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }
    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 8.dp, end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {

                        BackHandler(enabled = text.isNotEmpty(), onBack = {
                            text = ""
                            userViewModel.clearSearch()
                            focusManager.clearFocus()
                        })
                        SearchBar(modifier = Modifier.focusRequester(focusRequester),text = text, onTextChange = {
                            text = it
                            userViewModel.searchUser(it)
                        }, onDone = {
                            focusManager.clearFocus()
                        })

                    }


                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Recommended", color = Color.Gray)
                    LazyColumn {
                        items(users) {
                            UsersItem(
                                userData = it,
                                navController = navController,
                                showAllUsers = true,
                            )
                        }
                    }

                }
            }
        }
    )

}

