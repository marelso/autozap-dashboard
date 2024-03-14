package ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import components.actionServiceControlComponent
import components.headline0Component
import components.headline1Component
import components.subtitleComponent
import data.attendant.Attendant
import ui.home.items.attendantItemComponent

@Composable
fun homeScreen(
    modifier: Modifier = Modifier,
    isServiceRunning: Boolean,
    attendants: List<Attendant>,
    onAttendantClick: (Attendant) -> Unit,
    onReplyClick: () -> Unit,
    onCreateClick: () -> Unit,
    onStartServiceClick: () -> Unit,
    onStopServiceClick: () -> Unit,
    onAttendantDeleteClick: (Attendant) -> Unit
) {
    LazyColumn(modifier.padding(32.dp).fillMaxWidth()) {
        item {
            headline0Component(
                modifier = Modifier.padding(bottom = 8.dp),
                title = "AutoZap dashboard",
                hasUnderlineHighlight = true
            )
        }

        item {
            Row(modifier = Modifier.padding(bottom = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                subtitleComponent(
                    modifier = Modifier.weight(1f),
                    title = "Você pode gerenciar os serviços do AutoZap por aqui."
                )

            }
        }

        item {
            TextButton(
                modifier = Modifier.width(250.dp).height(70.dp).padding(bottom = 16.dp),
                onClick = { onReplyClick() },
                content = { Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    text = "Regras para resposta",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )}
            )
        }

        item {
            actionServiceControlComponent(
                modifier = Modifier.padding(bottom = 16.dp),
                isServiceRunning = isServiceRunning,
                onStartClick = { onStartServiceClick() },
                onStopClick = { onStopServiceClick() }
            )
        }

        item {
            Row(
                modifier = Modifier.padding(bottom = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                headline1Component(
                    modifier = Modifier.weight(1f),
                    title = "Gerencie seus atendentes"
                )

                OutlinedButton(
                    onClick = { onCreateClick() }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "Adicione um novo atendente"
                    )
                }
            }
        }

        attendantsList(
            attendants = attendants,
            onDelete = { onAttendantDeleteClick(it) },
            onClick = { onAttendantClick(it) }
        )
    }
}

private fun LazyListScope.attendantsList(
    attendants: List<Attendant>,
    onDelete: (Attendant) -> Unit,
    onClick: (Attendant) -> Unit
) = items(
    count = attendants.size,
    itemContent = { index ->
        val attendant = attendants[index]
        attendantItemComponent(
            attendant = attendant,
            onClick = {
                onClick(it)
            },
            onDelete = { onDelete(it) }
        )
    }
)

fun hexToColor(hex: String) = Color(
    red = Integer.valueOf(hex.substring(1, 3), 16) / 255f,
    green = Integer.valueOf(hex.substring(3, 5), 16) / 255f,
    blue = Integer.valueOf(hex.substring(5, 7), 16) / 255f,
    alpha = 1.0f
)