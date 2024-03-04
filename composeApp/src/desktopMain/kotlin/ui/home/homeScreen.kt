package ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.headline1Component
import components.headline0Component
import components.subtitleComponent

@Composable
fun homeScreen(modifier: Modifier = Modifier) {
    Column(modifier.padding(32.dp)) {
        headline0Component(modifier = Modifier.padding(bottom = 8.dp), title = "Autozap dashboard", hasUnderlineHighlight = true)
        subtitleComponent(modifier = Modifier.padding(bottom = 32.dp), title = "Here you can manage autozap services")
        headline1Component(title = "Manage current attendants")
    }
}