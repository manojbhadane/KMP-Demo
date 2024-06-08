package org.company.app.utils

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object Cache {

    const val CACHE_ITEM_POSTS = "key_posts"

    private val cacheMap = mutableMapOf<String, Any>()
    private val mutex = Mutex()

    @Suppress("UNCHECKED_CAST")
    suspend fun <T> get(key: String): T? {
        return mutex.withLock {
            cacheMap[key] as? T
        }
    }

    suspend fun put(key: String, value: Any) {
        mutex.withLock {
            cacheMap[key] = value
        }
    }
}