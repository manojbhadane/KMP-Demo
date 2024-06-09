package org.company.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.company.app.data.local.DataStoreRepository
import org.company.app.data.local.createDataStore
import kotlin.random.Random

class LoginScreen(val context: Any? = null) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val scope = rememberCoroutineScope()
        val dataStoreRepository = remember {
            DataStoreRepository(dataStore = createDataStore(context = context))
        }
        var savedTimestamp: Long? by remember { mutableStateOf(null) }
        var isLoggedIn: Boolean by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
//            dataStoreRepository.readTimestamp().collectLatest {
//                savedTimestamp = it
//            }
            dataStoreRepository.isLoggedIn().collectLatest {
                isLoggedIn = it
            }
        }

        isLoggedIn.let {
            if (it) {
                navigator.push(LandingScreen(context))
            }
        }


        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            CommonToolbar(title = "Login")
            Text(text = "Saved value: ${if (savedTimestamp == null) "Empty" else "$savedTimestamp"}")
            Text(text = "Saved value isLoggedIn: ${"$isLoggedIn"}")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                scope.launch {
                    dataStoreRepository.saveTimestamp(
                        Random.nextLong(
                            from = 100,
                            until = 1000
                        )
                    )
                }
            }) {
                Text(text = "Save random number")
            }
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    //navigator.push(LandingScreen())
                    scope.launch {
                        val isSuccess = dataStoreRepository.saveIsLoggedIn(
                            isLoggedIn = true
                        )
                        if (isSuccess) {
                            isLoggedIn = true
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Don't have an account?", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign up")
            }
        }
    }
}
