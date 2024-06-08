package org.company.app.utils

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }
