package components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun actionServiceControlComponent(
    modifier: Modifier = Modifier,
    isServiceRunning: Boolean,
    onStartClick: () -> Unit,
    onStopClick: () -> Unit
) {
    val buttonText = if (isServiceRunning) "Stop" else "Start"
    val onClickAction = if (isServiceRunning) onStopClick else onStartClick

    OutlinedButtonWithVerticalLine(modifier = modifier, text = buttonText, onClick = onClickAction, checked = isServiceRunning)
}