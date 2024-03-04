package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun headline0Component(
    modifier: Modifier = Modifier,
    title: String,
    hasUnderlineHighlight: Boolean = false,
    color: Color? = null,
    fontWeight: Int = 700,
    maxLines: Int = 1,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start,
    underLineSpacing: Dp = 0.dp
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            maxLines = maxLines,
            overflow = textOverflow,
            textAlign = textAlign,
            style = MaterialTheme.typography.h4.copy(
                color = color ?: MaterialTheme.colors.onSurface,
                fontWeight = FontWeight(fontWeight)
            ),
        )
        if (hasUnderlineHighlight) {
            Box(modifier = Modifier
                .padding(top = underLineSpacing)
                .width(72.dp)
                .height(4.dp)
                .background(color = MaterialTheme.colors.primary)
            )
        }
    }
}