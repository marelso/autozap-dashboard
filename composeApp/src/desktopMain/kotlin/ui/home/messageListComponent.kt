package ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import data.Message

@Composable
fun messageListComponent(
    messages: List<Message>,
    current: Message?,
    padding: PaddingValues,
    onSelection: (Message) -> Unit,
    onDelete: (Int) -> Unit
) {
    LazyColumn(contentPadding = padding) {
        messageList(
            current = current,
            messages = messages,
            onSelection = { onSelection(it) },
            onDelete = { onDelete(it) })
    }
}

private fun LazyListScope.messageList(
    current: Message?,
    messages: List<Message>,
    onSelection: (Message) -> Unit,
    onDelete: (Int) -> Unit
) = items(
    count = messages.size,
    itemContent = { index ->
        val message = messages[index]
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelection(message) }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.Top),
                selected = message == current,
                onClick = { onSelection(message) }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = message.message,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                modifier = Modifier.align(Alignment.Top).padding(end = 8.dp),
                content = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Delete,
                        tint = hexToColor("#F26549"),
                        contentDescription = "Delete message"
                    )
                },
                onClick = { onDelete(message.id) }
            )
        }
    }
)