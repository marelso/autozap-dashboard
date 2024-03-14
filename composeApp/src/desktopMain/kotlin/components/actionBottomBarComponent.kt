package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun actionBottomBarComponent(
    modifier: Modifier = Modifier,
    leadingText: String? = null,
    trailingText: String? = null,
    leadingTextEnabled: Boolean = true,
    trailingTextEnabled: Boolean = false,
    arrangement:  Arrangement.Horizontal = Arrangement.Center,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth(),
        horizontalArrangement = arrangement,
    ) {
        leadingText?.let {
            TextButton(
                onClick = { onDismissRequest() },
                modifier = Modifier.padding(8.dp),
                enabled = leadingTextEnabled
            ) {
                Text(it)
            }
        }

        trailingText?.let {
            TextButton(
                onClick = { onConfirmation() },
                modifier = Modifier.padding(8.dp),
                enabled = trailingTextEnabled
            ) {
                Text(it)
            }
        }
    }
}