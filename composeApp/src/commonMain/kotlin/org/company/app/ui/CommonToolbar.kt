package org.company.app.ui

// CommonMain/src/commonMain/kotlin/com/example/components/CommonToolbar.kt

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonToolbar(title: String, onBackButtonClick: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBackButtonClick != null) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            } else null
        },
        modifier = Modifier.fillMaxWidth()
    )
}
