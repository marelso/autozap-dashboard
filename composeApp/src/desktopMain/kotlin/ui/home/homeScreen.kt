package ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.headline0Component
import components.headline1Component
import components.subtitleComponent
import data.Attendant
import ui.home.items.attendantItemComponent

@Composable
fun homeScreen(
    modifier: Modifier = Modifier,
    attendants: List<Attendant>,
    onAttendantClick: (Attendant) -> Unit,
    onCreateClick: () -> Unit
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
            subtitleComponent(
                modifier = Modifier.padding(bottom = 32.dp),
                title = "Here you can manage autozap services"
            )
        }
        item {
            Row(modifier = Modifier.padding(bottom = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                headline1Component(
                    modifier = Modifier.padding(bottom = 8.dp).weight(1f),
                    title = "Manage current attendants"
                )
                Box(
                    modifier = Modifier.padding(16.dp).size(50.dp).border(
                        border = BorderStroke(
                            width = 2.dp, color = MaterialTheme.colors.primary
                        ), shape = RoundedCornerShape(10.dp)
                    ), contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        content = {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                imageVector = Icons.Default.Add,
                                tint = MaterialTheme.colors.primary,
                                contentDescription = "Include"
                            )
                        },
                        onClick = { onCreateClick() }
                    )
                }
            }
        }

        attendantsList(
            attendants = attendants,
            onConfirmation = {},
            onDelete = {},
            onClick = {
                onAttendantClick(it)
            }
        )
    }
}

private fun LazyListScope.attendantsList(
    attendants: List<Attendant>,
    onConfirmation: () -> Unit,
    onDelete: () -> Unit,
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
            onDelete = { onDelete() }
        )
    }
)