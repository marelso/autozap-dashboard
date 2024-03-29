
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.actionReplyRulesDialogComponent
import components.actionSettingsDialogComponent
import data.Attendant
import data.DesktopDatabase
import data.WindowsServiceManager
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.home.attendantCreateComponent
import ui.home.attendantDetailComponent
import ui.home.attendantRemoveComponent
import ui.home.homeScreen

@Composable
@Preview
fun App() {
    val isServiceRunning = mutableStateOf(WindowsServiceManager.isRunning())
    val openAttendantDetail = mutableStateOf<Attendant?>(null)
    val openAttendantRemove = mutableStateOf<Attendant?>(null)
    val openAttendantCreate = mutableStateOf(false)
    val openSettingsDialog = mutableStateOf(false)
    val openReplyRules = mutableStateOf(false)

    Scaffold(
        modifier = Modifier.sizeIn(minWidth = 100.dp, minHeight = 100.dp),
        topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            title = {},
            actions = {
                OutlinedButton(
                    modifier = Modifier.padding(end = 32.dp),
                    onClick = { openSettingsDialog.value = true }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Settings,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "Abrir configurações"
                    )
                }
            })
    }) {
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
                onReplyClick = { openReplyRules.value = true }
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

            if (openSettingsDialog.value) actionSettingsDialogComponent {
                openSettingsDialog.value = false
            }

            if(openReplyRules.value) actionReplyRulesDialogComponent {
                openReplyRules.value = false
            }
        }
    }
}