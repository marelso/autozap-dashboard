package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
fun actionDialogComponent(
    title: String? = null,
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Scaffold(
            modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background),
            topBar = { actionTopBarComponent(onNavigationClicked = onDismissRequest, title = title) },
            content = { content() },
            bottomBar = {
                actionBottomBarComponent(
                    trailingText = "Confirm",
                    leadingText = "Cancel",
                    onConfirmation = onConfirmation,
                    onDismissRequest = onDismissRequest
                )
            }
        )
    }
}