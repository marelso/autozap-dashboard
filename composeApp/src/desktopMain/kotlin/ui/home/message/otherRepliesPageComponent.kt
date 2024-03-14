package ui.home.message

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import components.actionBottomBarComponent
import components.captionTextComponent
import data.DesktopDatabase
import data.message.Message
import data.reply.Reply
import ui.home.messageListComponent
import ui.home.replyListComponent

@Composable
fun otherRepliesPageComponent(onDismissRequest: () -> Unit) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val replyDao = DesktopDatabase.getInstance().getReplyDao()
    val repliesState by mutableStateOf(replyDao.fetch())
    val selectedOption = remember { mutableStateOf(repliesState.value.firstOrNull { it.active }) }
    val reply = remember { mutableStateOf(TextFieldValue()) }
    val isSaveEnabled by derivedStateOf {
        reply.value.text.isNotBlank()
    }
    val isApplyEnabled by derivedStateOf {
        selectedOption.value != null
    }
    var isSnackbarVisible by remember { mutableStateOf(false) }
    Scaffold(
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
                    value = reply.value,
                    onValueChange = { reply.value = it },
                    maxLines = 4,
                    label = { Text("Mensagem") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                Row {
                    Button(
                        enabled = isSaveEnabled,
                        onClick = {
                            replyDao.insert(
                                Reply(
                                    id = 0,
                                    reply = reply.value.text.trim(),
                                    active = true
                                )
                            )
                            repliesState.value = replyDao.fetch().value
                        },
                        content = { Text("Cadastrar") })
                }

                replyListComponent(
                    replies = repliesState.value,
                    current = selectedOption.value,
                    padding = it,
                    onSelection = { newSelectedOption ->
                        selectedOption.value = newSelectedOption
                        selectedOption.value?.let {
                            clipboardManager.setText(AnnotatedString(it.reply))
                            isSnackbarVisible = true
                        }
                    },
                    onDelete = {
                        replyDao.deleteById(it)
                        repliesState.value = replyDao.fetch().value
                    }
                )

            }
        },
        bottomBar = {
            actionBottomBarComponent(
                leadingText = "Cancelar",
                trailingText = "Aplicar",
                leadingTextEnabled = true,
                trailingTextEnabled = isApplyEnabled,
                arrangement = Arrangement.End,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = {}
            )
        }
    )
}