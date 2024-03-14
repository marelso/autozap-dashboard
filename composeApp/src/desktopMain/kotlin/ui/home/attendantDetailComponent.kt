package ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import components.actionDialogComponent
import data.attendant.Attendant

@Composable
fun attendantDetailComponent(
    attendant: Attendant,
    onConfirmation: (Attendant) -> Unit,
    onDismissRequest: () -> Unit
) {
    val name = remember { mutableStateOf(TextFieldValue(attendant.name)) }
    val bio = remember { mutableStateOf(TextFieldValue(attendant.bio.orEmpty())) }
    val link = remember { mutableStateOf(TextFieldValue(attendant.link)) }
    val isConfirmationEnabled = remember {
        derivedStateOf {
            (name.value.text != attendant.name || bio.value.text != attendant.bio || link.value.text != attendant.link).and(
                name.value.text.isNotBlank() && link.value.text.isNotBlank()
            )
        }
    }

    actionDialogComponent(
        title = attendant.name,
        content = { attendantContentComponent(name = name, bio = bio, link = link) },
        isConfirmationEnabled = isConfirmationEnabled.value,
        onConfirmation = {
            onConfirmation(
                Attendant(
                    id = attendant.id,
                    name = name.value.text.trim(),
                    bio = bio.value.text.trim(),
                    link = link.value.text.trim()
                )
            )
        },
        onDismissRequest = { onDismissRequest() }
    )
}