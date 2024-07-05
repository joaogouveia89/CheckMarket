package io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.presentation.CheckMarketAppBar
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketSecondaryVariant

@Composable
fun MarketListItemAddContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    onNavigateBack: () -> Unit
) {
    // State variables to hold user inputs
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(MarketItemCategory.FRUITS) }

    // Function to handle the save action
    val onSave = {
        // Logic to save the item
        onNavigateBack()
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        // Top App Bar
        CheckMarketAppBar(
            title = R.string.add_market_item,
            actionIcon = Icons.Default.Save,
            actionContentDescription = stringResource(R.string.save),
            onActionClick = {},
        )

        // Input Fields
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(id = R.string.product_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(text = stringResource(id = R.string.price)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text(text = stringResource(id = R.string.quantity)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Selection
        Text(
            text = stringResource(id = R.string.category),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(MarketItemCategory.entries.size) { categoryId ->

                val category = MarketItemCategory.entries[categoryId]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { selectedCategory = category }
                        .background(
                            if (selectedCategory == category) CheckMarketSecondaryVariant.copy(alpha = 0.2f)
                            else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = category.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = stringResource(id = category.nameRes),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentPreview() {
    MarketListItemAddContent(
        onNavigateBack = {}
    )
}
