package ui.home.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import components.actionSnackbarComponent
import components.captionTextComponent
import data.DesktopDatabase
import data.Message
import ui.home.messageListComponent

@Composable
fun firstReplyPageComponent(onDismissRequest: () -> Unit) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
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
    var isSnackbarVisible by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background).fillMaxSize().padding(32.dp),
        content = {
            Column {
                captionTextComponent(
                    text = "Você pode usar as variáveis",
                    maxLines = 1,
                    fontWeight = 700,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                captionTextComponent(
                    text = "\$nome, \$bio, \$contato",
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                OutlinedTextField(
                    value = message.value,
                    onValueChange = { message.value = it },
                    maxLines = 4,
                    label = { Text("Mensagem") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                Row {
                    Button(
                        enabled = isSaveEnabled,
                        onClick = {
                            messageDao.insert(
                                Message(
                                    id = 0,
                                    message = message.value.text.trim(),
                                    active = true
                                )
                            )
                            messagesState.value = messageDao.fetch().value
                        },
                        content = { Text("Cadastrar") })
                }

                messageListComponent(
                    messages = messagesState.value,
                    current = selectedOption.value,
                    padding = it,
                    onSelection = { newSelectedOption ->
                        selectedOption.value = newSelectedOption
                        selectedOption.value?.let {
                            clipboardManager.setText(AnnotatedString(it.message))
                            isSnackbarVisible = true
                        }
                    },
                    onDelete = {
                        messageDao.deleteById(it)
                        messagesState.value = messageDao.fetch().value
                    }
                )

            }
            actionSnackbarComponent(
                modifier = Modifier,
                isVisible = isSnackbarVisible,
                text = "Mensagem copiada!",
                onDismiss = { isSnackbarVisible = false }
            )
        },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colors.background)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(
                        content = { Text("Cancelar") },
                        onClick = { onDismissRequest() }
                    )
                    TextButton(
                        enabled = isApplyEnabled,
                        content = { Text("Aplicar") },
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