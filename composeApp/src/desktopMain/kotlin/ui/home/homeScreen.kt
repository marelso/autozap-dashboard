package ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.headline0Component
import components.headline1Component
import components.subtitleComponent
import data.Attendant
import data.DesktopDatabase

@Composable
fun homeScreen(modifier: Modifier = Modifier) {
    val attendantDao = DesktopDatabase.getInstance().getAttendantDao()
    val attendants = remember{
        mutableStateOf<List<Attendant>>(emptyList())
    }

    LaunchedEffect(Unit) {
        attendants.value = attendantDao.fetch().value
    }

    LazyColumn (modifier.padding(32.dp).fillMaxWidth()) {
        item { headline0Component(modifier = Modifier.padding(bottom = 8.dp), title = "Autozap dashboard", hasUnderlineHighlight = true) }
        item { subtitleComponent(modifier = Modifier.padding(bottom = 32.dp), title = "Here you can manage autozap services") }
        item { headline1Component(modifier = Modifier.padding(bottom = 8.dp), title = "Manage current attendants") }

        attendantsList(attendants.value)
    }
}

private fun LazyListScope.attendantsList(attendants: List<Attendant>) = items(
    count = attendants.size,
    itemContent = {
        val attendant = attendants[it]
        Text(text = "${attendant.id}: ${attendant.name}")
    }
)