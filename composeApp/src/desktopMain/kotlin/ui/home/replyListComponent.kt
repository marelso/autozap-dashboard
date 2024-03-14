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
import data.reply.Reply

@Composable
fun replyListComponent(
    replies: List<Reply>,
    current: Reply?,
    padding: PaddingValues,
    onSelection: (Reply) -> Unit,
    onDelete: (Int) -> Unit
) {
    LazyColumn(contentPadding = padding) {
        replyList(
            current = current,
            replies = replies,
            onSelection = { onSelection(it) },
            onDelete = { onDelete(it) })
    }
}

private fun LazyListScope.replyList(
    current: Reply?,
    replies: List<Reply>,
    onSelection: (Reply) -> Unit,
    onDelete: (Int) -> Unit
) = items(
    count = replies.size,
    itemContent = { index ->
        val reply = replies[index]
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSelection(reply) }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                modifier = Modifier.align(Alignment.Top),
                selected = reply == current,
                onClick = { onSelection(reply) }
            )
            Text(
                modifier = Modifier.weight(1f),
                text = reply.reply,
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
                onClick = { onDelete(reply.id) }
            )
        }
    }
)