package ui.home

import androidx.compose.material.DismissValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
fun fileDialog(
    parent: Frame? = null,
    onDismiss: () -> Unit,
    onSelectDirectory: (result: String?) -> Unit,
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                file?.let{
                    if (value) {
                        onSelectDirectory(File(directory, file).absolutePath)
                    }
                } ?: onDismiss()
            }
        }
    },
    dispose = { FileDialog::dispose }
)