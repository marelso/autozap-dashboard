package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun actionSettingsDialogComponent(
    onDismissRequest: () -> Unit,
    onAuthRequest: () -> Unit,
    onInstallServiceRequest: () -> Unit,
    onUninstallServiceRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.background).padding(32.dp)) {
            Column {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        modifier = Modifier.padding(vertical = 32.dp),
                        onClick = { onAuthRequest() },
                        content = {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                painter = painterResource("drawable/ic_qrcode.svg"),
                                contentDescription = "QRCODE"
                            )
                        })
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        onClick = { onInstallServiceRequest() },
                        content = { Text("Install service") }
                    )
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        onClick = { onUninstallServiceRequest() },
                        content = { Text("Uninstall service") }
                    )
                }
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}