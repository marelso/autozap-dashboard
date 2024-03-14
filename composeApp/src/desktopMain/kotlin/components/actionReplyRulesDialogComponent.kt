package components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ui.home.message.firstReplyPageComponent
import ui.home.message.otherRepliesPageComponent

@Composable
fun actionReplyRulesDialogComponent(onDismissRequest: () -> Unit) {
    val tabs = listOf("Primeira resposta", "Outras respostas")
    var selectedTabIndex by remember { mutableStateOf(0) }
    Dialog(onDismissRequest = onDismissRequest) {
        Column {
            TabRow(selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        modifier = Modifier.clickable {  selectedTabIndex = index  }.padding(16.dp)
                    ) {
                        Text(text = title, fontWeight = FontWeight.Bold)
                    }
                }
            }

            when (selectedTabIndex) {
                0 -> firstReplyPageComponent { onDismissRequest() }
                1 -> otherRepliesPageComponent { onDismissRequest() }
            }
        }
    }
}