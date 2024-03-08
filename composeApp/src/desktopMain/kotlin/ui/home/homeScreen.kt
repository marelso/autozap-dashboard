package ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.actionServiceControlComponent
import components.headline0Component
import components.headline1Component
import components.subtitleComponent
import data.Attendant
import ui.home.items.attendantItemComponent

@Composable
fun homeScreen(
    modifier: Modifier = Modifier,
    isServiceRunning: Boolean,
    attendants: List<Attendant>,
    onAttendantClick: (Attendant) -> Unit,
    onCreateClick: () -> Unit,
    onStartServiceClick: () -> Unit,
    onStopServiceClick: () -> Unit,
    onAttendantDeleteClick: (Attendant) -> Unit
) {
    LazyColumn(modifier.padding(32.dp).fillMaxWidth()) {
        item {
            headline0Component(
                modifier = Modifier.padding(bottom = 8.dp),
                title = "Autozap dashboard",
                hasUnderlineHighlight = true
            )
        }
        item {
            Row(modifier = Modifier.padding(bottom = 32.dp), verticalAlignment = Alignment.CenterVertically) {
                subtitleComponent(
                    modifier = Modifier.weight(1f),
                    title = "Here you can manage autozap services"
                )

            }
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
                    title = "Manage current attendants"
                )

                OutlinedButton(
                    onClick = { onCreateClick() }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "Include new attendant"
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