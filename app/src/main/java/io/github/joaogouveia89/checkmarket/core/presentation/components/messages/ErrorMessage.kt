package io.github.joaogouveia89.checkmarket.core.presentation.components.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketErrorRed

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    message: String,
    onDismiss: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(CheckMarketErrorRed)
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = message,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically)
                .padding(end = 16.dp)
                .clickable { onDismiss() },
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorMessagePreview() {
    ErrorMessage(
        message = "ERROR",
        onDismiss = {}
    )
}