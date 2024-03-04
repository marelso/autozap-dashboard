package components.data

import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarActions(
    val actionIcon: ImageVector,
    val actionCallback: () -> Unit,
    val actionIconContentDescription: String,
    val isEnabled: Boolean = true
)