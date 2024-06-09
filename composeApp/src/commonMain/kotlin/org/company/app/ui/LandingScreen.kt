package org.company.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.company.app.data.local.DataStoreRepository
import org.company.app.data.local.createDataStore
import org.company.app.model.Post
import org.company.app.repository.PostRepository
import org.company.app.ui.common.Chip
import org.company.app.ui.common.LayoutWithBars
import org.company.app.utils.capitalizeWords

class LandingScreen(val context: Any? = null) : Screen {

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val dataStoreRepository = remember {
            DataStoreRepository(dataStore = createDataStore(context = context))
        }
        val navigator = LocalNavigator.currentOrThrow

        LayoutWithBars(title = "Hi, Manoj") { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Chip(name = "Posts", modifier = Modifier.padding(bottom = 12.dp).clickable {
                    navigator.push(DashboardScreen())
                })
                Chip(name = "Jobs", modifier = Modifier.padding(bottom = 12.dp))
                Chip(name = "Profile", modifier = Modifier.padding(bottom = 12.dp))
                Chip(name = "Settings", modifier = Modifier.padding(bottom = 12.dp))
                Chip(name = "Logout", modifier = Modifier.padding(bottom = 12.dp).clickable {
                    navigator.push(DashboardScreen())
                    scope.launch {
                        dataStoreRepository.saveIsLoggedIn(
                            isLoggedIn = false
                        )
                    }
                })
            }
        }
    }

}


