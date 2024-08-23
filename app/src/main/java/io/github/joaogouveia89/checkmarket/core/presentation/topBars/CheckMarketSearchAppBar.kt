package io.github.joaogouveia89.checkmarket.core.presentation.topBars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.joaogouveia89.checkmarket.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DEBOUNCE_PERIOD: Long = 300L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckMarketSearchAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onQueryChange: (String) -> Unit,
) {
    var query by rememberSaveable { mutableStateOf("") }
    var debounceJob: Job? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .clickable { onBackClick() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(
                    id = R.string.back
                )
            )
        },
        title = {
            val focusRequester = remember { FocusRequester() }

            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                value = query,
                onValueChange = { newQuery ->
                    query = newQuery
                    debounceJob?.cancel()
                    debounceJob = coroutineScope.launch {
                        delay(DEBOUNCE_PERIOD)
                        onQueryChange(query)
                    }
                }
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        },
        colors = checkMarketTopBarColors()
    )
}

@Preview
@Composable
private fun CheckMarketSearchAppBarPreview() {
    CheckMarketSearchAppBar(
        onBackClick = {},
        onQueryChange = {}
    )
}