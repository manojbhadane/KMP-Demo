package org.company.app.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.company.app.model.Post
import org.company.app.utils.Cache
import org.company.app.utils.Cache.CACHE_ITEM_POSTS
import org.company.app.utils.capitalizeWords

class PostRepository {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun fetchPosts(): List<Post> {
        /*if (!NetworkUtils.isNetworkAvailable()) {
            throw Exception("No internet connection")
        }*/

        val cachedPosts: List<Post>? = Cache.get(CACHE_ITEM_POSTS)
        if (cachedPosts != null) {
            return cachedPosts
        }

        val posts: List<Post> = client.get("https://jsonplaceholder.typicode.com/posts")
            .body()
        val modifiedPosts = posts.map {
            it.copy(
                title = it.title.capitalizeWords(),
                body = it.body.capitalizeWords()
            )
        }
        Cache.put(CACHE_ITEM_POSTS, modifiedPosts)
        return modifiedPosts
    }
}