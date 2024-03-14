package ui.home.attendant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.attendant.Attendant

@Composable
fun attendantRemoveComponent(
    attendant: Attendant,
    onConfirmation: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    val checked = remember { mutableStateOf(false) }

    AlertDialog(
        title = {
            Text(text = "Removendo atendente: ${attendant.name} ")
        },
        text = {
            Column {
                Text(text = "Depois de excluir um atendente, não há como reverter. Por favor, tenha certeza.")
                Row(modifier = Modifier.padding(top = 12.dp).clickable { checked.value = !checked.value }, verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checked.value,
                        onCheckedChange = { isChecked -> checked.value = isChecked }
                    )
                    Text(text = "Eu li e compreendi as consequências.", modifier = Modifier.padding(end =20.dp))
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onConfirmation(attendant.id) }, enabled = checked.value) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Cancerlar")
            }
        }
    )
}