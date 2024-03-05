package ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import components.actionDialogComponent
import data.Attendant

@Composable
fun attendantDetailComponent(
    attendant: Attendant,
    onConfirmation: (Attendant) -> Unit,
    onDismissRequest: () -> Unit
) {
    val name = remember { mutableStateOf(TextFieldValue(attendant.name)) }
    val bio = remember { mutableStateOf(TextFieldValue(attendant.bio)) }
    val link = remember { mutableStateOf(TextFieldValue(attendant.link)) }
    val isConfirmationEnabled = remember {
        derivedStateOf {
            (name.value.text != attendant.name || bio.value.text != attendant.bio || link.value.text != attendant.link).and(
                name.value.text.isNotBlank() && bio.value.text.isNotBlank() && link.value.text.isNotBlank()
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
                    name = name.value.text,
                    bio = bio.value.text,
                    link = link.value.text
                )
            )
        },
        onDismissRequest = { onDismissRequest() }
    )
}