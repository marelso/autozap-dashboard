package components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import kotlinx.coroutines.delay

@Composable
fun actionSnackbarComponent(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    text: String,
    shape: Shape = MaterialTheme.shapes.small,
    autoDismiss: Boolean = true,
    timeout: Long = 1200,
    onDismiss: () -> Unit
) {
    if(autoDismiss) {
        LaunchedEffect(isVisible) {
            delay(timeout)
            onDismiss()
        }
    }

    if(isVisible) {
        Snackbar(
            modifier = modifier,
            content = { Text(text) },
            shape = shape,
            action = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Fechar")
                }
            }
        )
    }
}