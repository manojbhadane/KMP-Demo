package org.company.app.model

import kotlinx.serialization.Serializable
import org.company.app.utils.pickRandomItemsFromList

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val tags : List<String> = pickRandomItemsFromList(listOf("android", "java", "kotlin", "spring boot", "git", "dart", "flutter", "react", "angular"), 3)
)
