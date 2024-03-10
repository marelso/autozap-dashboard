package ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
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
) {
    LazyColumn(contentPadding = padding) {
        messageList(current = current, messages = messages, onSelection = { onSelection(it) })
    }
}

private fun LazyListScope.messageList(
    current: Message?,
    messages: List<Message>,
    onSelection: (Message) -> Unit
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
                selected = message == current,
                onClick = { onSelection(message) }
            )
            Text(
                text = message.message,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
)