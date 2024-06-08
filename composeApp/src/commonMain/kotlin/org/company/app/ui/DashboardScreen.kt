package org.company.app.ui

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
import org.company.app.model.Post
import org.company.app.repository.PostRepository
import org.company.app.ui.common.LayoutWithBars
import org.company.app.utils.capitalizeWords

class DashboardScreen : Screen {
    private val dummyData = List(20) { "Item #${it + 1}" }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val postRepository = remember { PostRepository() }
        var posts by remember { mutableStateOf<List<Post>>(emptyList()) }
        var isLoading by remember { mutableStateOf(true) }
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            scope.launch {
                try {
                    posts = postRepository.fetchPosts()
                } catch (e: Exception) {
                    // Handle the exception (e.g., show an error message)
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            }
        }

        LayoutWithBars(title = "Dashboard") { padding ->
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(posts) { post ->
                        PostItem(post)
                        Divider()
                    }
                }
            }
        }

        /*Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            CommonToolbar(title = "Dashboard")
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(dummyData) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Divider()
                }
            }
        }*/
    }

    @Composable
    fun PostItem(post: Post) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.body, style = MaterialTheme.typography.bodySmall)
        }
    }

}


