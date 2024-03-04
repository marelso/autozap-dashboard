import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.KoinApplication
import org.koin.core.context.KoinContext

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Autozap dashboard") {
        App()
    }
}