package org.company.app.model

// CommonMain/src/commonMain/kotlin/com/example/model/Post.kt

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
