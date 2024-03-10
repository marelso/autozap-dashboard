package components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun captionTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textOverflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = MaterialTheme.colors.onSurface,
    fontWeight: Int = 400,
    maxLines: Int = 2
) = Text(
    text = text,
    maxLines = maxLines,
    overflow = textOverflow,
    textAlign = textAlign,
    style = MaterialTheme.typography.caption.copy(
        color = color,
        fontWeight = FontWeight(fontWeight)
    ),
    modifier = modifier
)