package ui.home.message

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import components.actionBottomBarComponent

@Composable
fun otherRepliesPageComponent(onDismissRequest: () -> Unit) {
    val isApplyEnabled by derivedStateOf {
        true
    }
    Scaffold(
        content = {},
        bottomBar = {
            actionBottomBarComponent(
                leadingText = "Cancelar",
                trailingText = "Aplicar",
                leadingTextEnabled = true,
                trailingTextEnabled = isApplyEnabled,
                arrangement = Arrangement.End,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = {}
            )
        }
    )
}