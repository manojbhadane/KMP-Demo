package org.company.app

import androidx.compose.runtime.compositionLocalOf
import cafe.adriel.voyager.navigator.Navigator

val LocalNavigator = compositionLocalOf<Navigator?> { null }