package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ui.home.hexToColor

@Composable
fun OutlinedButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onClick: () -> Unit
) {
    val color = if (!checked) hexToColor("#F26549") else MaterialTheme.colors.primary
    Box(
        modifier = modifier
            .height(48.dp)
    ) {
        Box(
            modifier = Modifier
                .width(8.dp)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .background(color)
        )

        TextButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterStart),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = color
            ),
            shape = RoundedCornerShape(10)
        ) {
            Text(
                modifier = Modifier.weight(1f).padding(start = 16.dp),
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Switch(
                checked = checked,
                onCheckedChange = { onClick() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.primary,
                    checkedTrackColor = Color(0xFF6200EE),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray
                )
            )
        }
    }
}