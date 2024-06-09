package org.company.app.utils

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

fun <T> pickRandomItemsFromList(list: List<T>, numberOfItems: Int): List<T> {
    if (list.size <= numberOfItems) {
        return list
    }
    return list.shuffled().take(numberOfItems)
}