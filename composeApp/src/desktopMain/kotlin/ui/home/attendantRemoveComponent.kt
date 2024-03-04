package ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.Attendant

@Composable
fun attendantRemoveComponent(
    attendant: Attendant,
    onConfirmation: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    val checked = remember { mutableStateOf(false) }

    AlertDialog(
        title = {
            Text(text = "Deleting attendant: ${attendant.name} ")
        },
        text = {
            Column {
                Text(text = "Once you delete an attendant, there is no going back. Please be certain.")
                Row(modifier = Modifier.clickable { checked.value = !checked.value }, verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checked.value,
                        onCheckedChange = { isChecked -> checked.value = isChecked }
                    )
                    Text("I have read and understand these effects.")
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onConfirmation(attendant.id) }, enabled = checked.value) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Dismiss")
            }
        }
    )
}