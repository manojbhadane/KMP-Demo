package org.company.app.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.company.app.ui.CommonToolbar

@Composable
fun LayoutWithBars(
    title: String,
    onBackButtonClick: (() -> Unit)? = null,
    content: @Composable BoxScope.(PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            CommonToolbar(title, onBackButtonClick)
        },
        bottomBar = {
            // Here you can define your BottomNavigationBar if needed
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content(innerPadding)
        }
    }
}
