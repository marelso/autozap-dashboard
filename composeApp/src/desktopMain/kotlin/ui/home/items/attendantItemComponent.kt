package ui.home.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import data.Attendant

@Composable
fun attendantItemComponent(
    modifier: Modifier = Modifier,
    attendant: Attendant,
    onClick: (Attendant) -> Unit,
    onDelete: (Attendant) -> Unit
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(112.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .clickable {
            onClick(attendant)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(80.dp),
                imageVector = Icons.Outlined.AccountBox,
                contentDescription = "Attendant account",
                tint = MaterialTheme.colors.primary
            )
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = attendant.name)
                Text(text = attendant.bio, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Text(text = attendant.link, overflow = TextOverflow.Ellipsis, maxLines = 1)
            }
            IconButton(
                content = { Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Filled.Delete,
                    tint = Color.Red,
                    contentDescription = "Favorite"
                ) },
                onClick = { onDelete(attendant) }
            )
        }
    }
}