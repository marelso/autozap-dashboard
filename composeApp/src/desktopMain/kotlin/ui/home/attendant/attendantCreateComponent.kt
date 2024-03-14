package ui.home.attendant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import components.actionDialogComponent
import data.attendant.Attendant

@Composable
fun attendantCreateComponent(
    onConfirmation: (Attendant) -> Unit,
    onDismissRequest: () -> Unit
) {
    val name = remember { mutableStateOf(TextFieldValue()) }
    val bio = remember { mutableStateOf(TextFieldValue()) }
    val link = remember { mutableStateOf(TextFieldValue()) }
    val isConfirmationEnabled = remember {
        derivedStateOf {
            (name.value.text.isNotBlank()) && (link.value.text.isNotBlank())
        }
    }

    actionDialogComponent(
        title = "Cadastrar novo atendente",
        content = { attendantContentComponent(name = name, bio = bio, link = link) },
        isConfirmationEnabled = isConfirmationEnabled.value,
        onConfirmation = {
            onConfirmation(
                Attendant(
                    id = 0,
                    name = name.value.text.trim(),
                    bio = bio.value.text.trim(),
                    link = link.value.text.trim()
                )
            )
        },
        onDismissRequest = { onDismissRequest() }
    )
}