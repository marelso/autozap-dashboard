package ui.home

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import components.actionDialogComponent
import data.Attendant

@Composable
fun attendantDetailComponent(
    attendant: Attendant,
    onConfirmation: (Int, String, String, String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val name = remember { mutableStateOf(TextFieldValue()) }
    val bio = remember { mutableStateOf(TextFieldValue()) }
    val link = remember { mutableStateOf(TextFieldValue()) }
    val isConfirmationEnabled = remember {
        derivedStateOf {
            (name.value.text.isNotBlank()) && (bio.value.text.isNotBlank()) && (link.value.text.isNotBlank())
        }
    }

    actionDialogComponent(
        title = attendant.name,
        content = { attendantContentComponent(attendant = attendant, name = name, bio = bio, link = link) },
        isConfirmationEnabled = isConfirmationEnabled.value,
        onConfirmation = { onConfirmation(attendant.id, name.value.text, bio.value.text, link.value.text) },
        onDismissRequest = { onDismissRequest() }
    )
}