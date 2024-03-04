import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.Attendant
import data.DesktopDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.home.attendantCreateComponent
import ui.home.attendantDetailComponent
import ui.home.homeScreen

@Composable
@Preview
fun App() {
    val openAttendantDetail = mutableStateOf<Attendant?>(null)
    val openAttendantCreate = mutableStateOf(false)
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val attendantDao = DesktopDatabase.getInstance().getAttendantDao()
            val attendants = MutableStateFlow(attendantDao.fetch())

            homeScreen(
                attendants = attendants.value.value,
                onAttendantClick = { openAttendantDetail.value = it },
                onCreateClick = { openAttendantCreate.value = true }
            )

            openAttendantDetail.value?.let {
                attendantDetailComponent(
                    attendant = it,
                    onConfirmation = { attendant ->
                        println("${attendant.id} ${attendant.name} ${attendant.bio} ${attendant.link}")
                        attendantDao.update(attendant)
                        openAttendantDetail.value = null
                    },
                    onDismissRequest = { openAttendantDetail.value = null },
                )
            }

            if (openAttendantCreate.value) {
                attendantCreateComponent(
                    onConfirmation = {},
                    onDismissRequest = { openAttendantCreate.value = false }
                )
            }
        }
    }
}