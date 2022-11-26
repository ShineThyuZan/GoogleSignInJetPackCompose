package com.omgea.googlesigninsay.utlis

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.api.ApiException
import com.omgea.googlesigninsay.GoogleSignInButtonUi
import com.omgea.googlesigninsay.HomeScreen
import com.omgea.googlesigninsay.model.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit
) {
    Scaffold {
        Modifier.padding(it)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleSignInButtonUi(
                text = "Sign Up With Google",
                loadingText = "Signing In...",
                onClicked = { onClick() }
            )
            errorText?.let { errorText ->
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = errorText)
            }
        }
    }
}

@Composable
fun AuthScreen(authViewModel: AuthViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf<String?>(null)
    }
    val user by remember(authViewModel) {
        authViewModel.user
    }.collectAsState()
    val signInRequestCode = 1
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        authViewModel.signIn(
                            email = account.email!!,
                            displayName = account.displayName!!,
                            profileUrl = account.photoUrl.toString()
                        )
                    }
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
            }
        }
    AuthView(errorText = text, onClick = {
        text = null
        authResultLauncher.launch(signInRequestCode)
    })
    user?.let { userVo ->
        HomeScreen(user = userVo)
    }
}