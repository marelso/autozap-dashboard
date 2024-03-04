package ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
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
    onAttendantClick: (Attendant) -> Unit
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
        item { headline1Component(modifier = Modifier.padding(bottom = 8.dp), title = "Manage current attendants") }

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