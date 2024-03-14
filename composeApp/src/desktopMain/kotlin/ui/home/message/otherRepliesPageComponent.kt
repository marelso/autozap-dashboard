package ui.home.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import components.actionBottomBarComponent
import components.actionSnackbarComponent
import components.captionTextComponent
import data.DesktopDatabase
import data.reply.Reply
import ui.home.hexToColor

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
        modifier = Modifier.background(color = MaterialTheme.colors.background).fillMaxSize().padding(horizontal = 16.dp).padding(top = 8.dp),
        content = {
            Column {
                Row {
                    Icon(
                        modifier = Modifier.size(24.dp).align(Alignment.Top),
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Cuidado",
                        tint = hexToColor("#F26549")
                    )
                    captionTextComponent(
                        text = "Não utilize as variáveis da primeira resposta aqui!",
                        maxLines = 3,
                        fontWeight = 700,
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp).align(Alignment.Top)
                    )
                }

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
            actionSnackbarComponent(
                modifier = Modifier,
                isVisible = isSnackbarVisible,
                text = "Mensagem copiada!",
                onDismiss = { isSnackbarVisible = false }
            )
        },
        bottomBar = {
            actionBottomBarComponent(
                leadingText = "Cancelar",
                trailingText = "Aplicar",
                leadingTextEnabled = true,
                trailingTextEnabled = isApplyEnabled,
                arrangement = Arrangement.End,
                onDismissRequest = { onDismissRequest() },
                onConfirmation = {
                    selectedOption.value?.let {
                        replyDao.setActive(it.id)
                        onDismissRequest()
                    }}
            )
        }
    )
}