package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.WindowsServiceManager


@Composable
fun actionSettingsDialogComponent(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.background).padding(32.dp)) {
            Column {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        modifier = Modifier.padding(vertical = 24.dp),
                        onClick = { WindowsServiceManager.auth() },
                        content = {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                painter = painterResource("drawable/ic_qrcode.svg"),
                                contentDescription = "QRCODE"
                            )
                        })

                    captionTextComponent(
                        text = "Scaneie o QRCODE após clicar no botão acima e, em seguida, instale o serviço.",
                        maxLines = Int.MAX_VALUE,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 32.dp)
                    )

                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        onClick = { WindowsServiceManager.install() },
                        content = { Text("Instalar serviço") }
                    )
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        onClick = { WindowsServiceManager.uninstall() },
                        content = { Text("Desinstalar serviço") }
                    )
                }
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Retornar")
                }
            }
        }
    }
}