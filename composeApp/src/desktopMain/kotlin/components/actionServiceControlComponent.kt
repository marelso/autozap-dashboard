package components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun actionServiceControlComponent(
    modifier: Modifier = Modifier,
    isServiceRunning: Boolean,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    val buttonText = if (isServiceRunning) "Parar serviço" else "Iniciar serviço"
    val onClickAction = if (isServiceRunning) onStopClick else onStartClick

    OutlinedButtonComponent(modifier = modifier, text = buttonText, onClick = onClickAction, checked = isServiceRunning)
}