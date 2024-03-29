package ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun attendantContentComponent(
    modifier: Modifier = Modifier,
    name: MutableState<TextFieldValue>,
    bio: MutableState<TextFieldValue>,
    link: MutableState<TextFieldValue>
) {
    Column(modifier.padding(8.dp)) {
        OutlinedTextField(value = name.value, onValueChange = {
            name.value = it
        }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(value = bio.value,
            onValueChange = { bio.value = it },
            label = { Text("Bio") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(value = link.value,
            onValueChange = { link.value = it },
            label = { Text("Contato") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}