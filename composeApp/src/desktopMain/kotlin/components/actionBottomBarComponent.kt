package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    arrangement:  Arrangement.HorizontalOrVertical = Arrangement.Center,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = arrangement,
    ) {
        leadingText?.let {
            TextButton(
                onClick = { onDismissRequest() },
                modifier = Modifier.padding(8.dp),
            ) {
                Text(it)
            }
        }

        trailingText?.let {
            TextButton(
                onClick = { onConfirmation() },
                modifier = Modifier.padding(8.dp),
            ) {
                Text(it)
            }
        }
    }
}