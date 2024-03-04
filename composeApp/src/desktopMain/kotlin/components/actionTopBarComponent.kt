package components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import components.data.TopBarActions

@Composable
fun actionTopBarComponent(
    modifier: Modifier = Modifier,
    title: String?,
    navigationIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    actions: List<TopBarActions>? = null,
    onNavigationClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            title?.let {
                Text(text = it)
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = { onNavigationClicked() }) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = "Navigate to previous screen",
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            actions?.map {
                IconButton(
                    enabled = it.isEnabled,
                    onClick = { it.actionCallback() }
                ) {
                    Icon(
                        imageVector = it.actionIcon,
                        contentDescription = it.actionIconContentDescription,
                        tint = if (it.isEnabled) {
                            MaterialTheme.colors.primary
                        } else {
                            MaterialTheme.colors.onSurface
                        }
                    )
                }
            }
        }
    )
}