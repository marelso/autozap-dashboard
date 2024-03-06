import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.Attendant
import data.DesktopDatabase
import data.WindowsServiceManager
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.home.*

@Composable
@Preview
fun App() {
    val openAttendantDetail = mutableStateOf<Attendant?>(null)
    val openAttendantRemove = mutableStateOf<Attendant?>(null)
    val openAttendantCreate = mutableStateOf(false)
    val openFileDialog = mutableStateOf(false)
    val isServiceRunning = mutableStateOf(WindowsServiceManager.isRunning())

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val attendantDao = DesktopDatabase.getInstance().getAttendantDao()
            val attendants = MutableStateFlow(attendantDao.fetch())

            homeScreen(
                attendants = attendants.value.value,
                isServiceRunning = isServiceRunning.value,
                onAttendantClick = { openAttendantDetail.value = it },
                onCreateClick = { openAttendantCreate.value = true },
                onAttendantDeleteClick = { openAttendantRemove.value = it },
                onStartServiceClick = {
                    WindowsServiceManager.start()
                    isServiceRunning.value = WindowsServiceManager.isRunning()
                },
                onStopServiceClick = {
                    WindowsServiceManager.stop()
                    isServiceRunning.value = WindowsServiceManager.isRunning()
                },
                onOpenFileDialogClick = { openFileDialog.value = true }
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
                    onConfirmation = {
                        attendantDao.insert(it)
                        openAttendantCreate.value = false
                    },
                    onDismissRequest = { openAttendantCreate.value = false }
                )
            }

            openAttendantRemove.value?.let {
                attendantRemoveComponent(
                    attendant = it,
                    onConfirmation = {
                        attendantDao.deleteById(it)
                        openAttendantRemove.value = null
                    },
                    onDismissRequest = { openAttendantRemove.value = null }
                )
            }

            if (openFileDialog.value) {
                fileDialog(
                    onSelectDirectory = { openFileDialog.value = false },
                    onDismiss = { openFileDialog.value = false }
                )
            }
        }
    }
}