import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import data.Attendant
import data.DesktopDatabase
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.home.attendantDetailComponent
import ui.home.homeScreen

@Composable
@Preview
fun App() {
    val openAttendantDetail = mutableStateOf<Attendant?>(null)
    val attendantDao = DesktopDatabase.getInstance().getAttendantDao()


    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val attendants = remember {
                mutableStateOf<List<Attendant>>(emptyList())
            }

            LaunchedEffect(Unit) {
                attendants.value = attendantDao.fetch().value
            }

            homeScreen(
                attendants = attendants.value,
                onAttendantClick = { openAttendantDetail.value = it }
            )

            openAttendantDetail.value?.let {
                attendantDetailComponent(
                    attendant = it,
                    onConfirmation = { id, name, bio, link ->
                        println("$id $name $bio $link")
                    },
                    onDismissRequest = { openAttendantDetail.value = null },
                )
            }
        }
    }
}