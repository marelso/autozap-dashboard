package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.DesktopDatabase
import data.Message
import ui.home.messageListComponent

@Composable
fun actionReplyRulesDialogComponent(onDismissRequest: () -> Unit) {
    val messageDao = DesktopDatabase.getInstance().getMessageDao()
    val messagesState by mutableStateOf(messageDao.fetch())
    val selectedOption = remember { mutableStateOf(messagesState.value.firstOrNull { it.active }) }
    val message = remember { mutableStateOf(TextFieldValue()) }
    val isSaveEnabled by derivedStateOf {
        message.value.text.isNotBlank()
    }
    val isApplyEnabled by derivedStateOf {
        selectedOption.value != null
    }
    Dialog(onDismissRequest = onDismissRequest) {
        Scaffold(
            modifier = Modifier.background(color = MaterialTheme.colors.background).fillMaxSize().padding(32.dp),
            content = {
                Column {
                    OutlinedTextField(
                        value = message.value,
                        onValueChange = { message.value = it },
                        maxLines = 3,
                        label = { Text("Message") },
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    )

                    Row {
                        Button(
                            enabled = isSaveEnabled,
                            onClick = {
                                val message = Message(
                                    id = 0,
                                    message = message.value.text,
                                    active = true
                                )
                                messageDao.insert(message)
                                messagesState.value = messageDao.fetch().value
                            },
                            content = {
                                Text("Save")
                            })
                    }

                    messageListComponent(
                        messages = messagesState.value,
                        current = selectedOption.value,
                        padding = it,
                        onSelection = { newSelectedOption ->
                            selectedOption.value = newSelectedOption
                        }
                    )

                }
            },
            bottomBar = {
                Box(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colors.background)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(
                            content = { Text("Dismiss") },
                            onClick = { onDismissRequest() }
                        )
                        TextButton(
                            enabled = isApplyEnabled,
                            content = { Text("Apply") },
                            onClick = {
                                selectedOption.value?.let {
                                    messageDao.setActive(it.id)
                                    onDismissRequest()
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}