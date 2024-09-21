package io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.joaogouveia89.checkmarket.R
import io.github.joaogouveia89.checkmarket.core.model.MarketItemCategory
import io.github.joaogouveia89.checkmarket.core.presentation.components.messages.ErrorMessage
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateFields
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.model.ItemCreateSaveUiModel
import io.github.joaogouveia89.checkmarket.marketListItemCreate.presentation.state.ItemCreateState
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketErrorRed
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketSecondaryVariant
import io.github.joaogouveia89.checkmarket.ui.theme.CheckMarketTheme

@Composable
fun ItemCreateContent(
    modifier: Modifier = Modifier,
    uiState: ItemCreateState,
    onSaveClick: (item: ItemCreateSaveUiModel) -> Unit,
    onErrorDismiss: () -> Unit
) {
    // State variables to hold user inputs
    var name by rememberSaveable { mutableStateOf(uiState.item.name) }
    var price by rememberSaveable { mutableStateOf(uiState.item.price) }
    var quantity by rememberSaveable { mutableStateOf(uiState.item.quantity) }
    var selectedCategory by rememberSaveable { mutableStateOf(uiState.item.category) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
        ) {

            // Input Fields
            Spacer(modifier = Modifier.height(16.dp))

            val isNameInvalid = uiState.invalidFields.contains(ItemCreateFields.NAME)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(id = R.string.product_name)) },
                isError = isNameInvalid,
                singleLine = true,
                trailingIcon = {
                    if (isNameInvalid) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = CheckMarketErrorRed
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            val isPriceInvalid = uiState.invalidFields.contains(ItemCreateFields.PRICE)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = price,
                onValueChange = { price = it },
                label = { Text(text = stringResource(id = R.string.price)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                isError = isPriceInvalid,
                singleLine = true,
                trailingIcon = {
                    if (isPriceInvalid) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = CheckMarketErrorRed
                        )
                    }
                }

            )

            Spacer(modifier = Modifier.height(16.dp))

            val isQuantityInvalid = uiState.invalidFields.contains(ItemCreateFields.QUANTITY)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text(text = stringResource(id = R.string.quantity)) },
                isError = isQuantityInvalid,
                singleLine = true,
                trailingIcon = {
                    if (isQuantityInvalid) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = CheckMarketErrorRed
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Category Selection
            CategorySelection(
                selected = selectedCategory,
                onCategorySelected = {
                    selectedCategory = it
                }
            )

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                SaveButton(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    name = name,
                    price = price,
                    quantity = quantity,
                    category = selectedCategory,
                    onSaveClick = onSaveClick
                )
            }
        }

        uiState.errorRes?.let {
            ErrorMessage(
                modifier = modifier
                    .align(Alignment.BottomCenter),
                message = stringResource(id = it),
                onDismiss = onErrorDismiss
            )
        }
    }
}

@Composable
private fun SaveButton(
    modifier: Modifier = Modifier,
    onSaveClick: (item: ItemCreateSaveUiModel) -> Unit,
    name: String,
    price: String,
    quantity: String,
    category: MarketItemCategory
) {
    Button(
        modifier = modifier,
        onClick = {
            onSaveClick(
                ItemCreateSaveUiModel(
                    name = name,
                    price = price,
                    quantity = quantity,
                    category = category
                )
            )
        }
    ) {
        Text(text = stringResource(id = R.string.save))
    }
}

@Composable
private fun CategorySelection(
    selected: MarketItemCategory,
    onCategorySelected: (MarketItemCategory) -> Unit = {}
) {

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
                    .clickable { onCategorySelected(category) }
                    .background(
                        if (selected == category) CheckMarketSecondaryVariant.copy(alpha = 0.2f)
                        else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = category.icon,
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

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentPreview() {
    CheckMarketTheme {
        ItemCreateContent(
            uiState = ItemCreateState(),
            onSaveClick = {},
            onErrorDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentLoadingPreview() {
    CheckMarketTheme {
        val uiState by remember {
            mutableStateOf(
                ItemCreateState(
                    isLoading = true,
                    item = ItemCreateSaveUiModel(
                        category = MarketItemCategory.HYGIENE
                    )
                )
            )
        }
        ItemCreateContent(
            uiState = uiState,
            onSaveClick = {},
            onErrorDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentErrorMessagePreview() {
    CheckMarketTheme {
        var uiState by remember {
            mutableStateOf(
                ItemCreateState(
                    errorRes = R.string.error_invalid_name,
                    item = ItemCreateSaveUiModel(
                        category = MarketItemCategory.SNACKS
                    )
                )
            )
        }
        ItemCreateContent(
            uiState = uiState,
            onSaveClick = {},
            onErrorDismiss = {
                uiState = uiState.copy(
                    errorRes = null,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentInvalidNamePreview() {
    CheckMarketTheme {
        var uiState by remember {
            mutableStateOf(
                ItemCreateState(
                    invalidFields = listOf(
                        ItemCreateFields.NAME
                    ),
                    item = ItemCreateSaveUiModel(
                        category = MarketItemCategory.SNACKS
                    )
                )
            )
        }
        ItemCreateContent(
            uiState = uiState,
            onSaveClick = {},
            onErrorDismiss = {
                uiState = uiState.copy(
                    errorRes = null,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentInvalidPricePreview() {
    CheckMarketTheme {
        var uiState by remember {
            mutableStateOf(
                ItemCreateState(
                    invalidFields = listOf(
                        ItemCreateFields.PRICE
                    ),
                    item = ItemCreateSaveUiModel(
                        category = MarketItemCategory.SNACKS
                    )
                )
            )
        }
        ItemCreateContent(
            uiState = uiState,
            onSaveClick = {},
            onErrorDismiss = {
                uiState = uiState.copy(
                    errorRes = null,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentInvalidQuantityPreview() {
    CheckMarketTheme {
        var uiState by remember {
            mutableStateOf(
                ItemCreateState(
                    invalidFields = listOf(
                        ItemCreateFields.QUANTITY
                    ),
                    item = ItemCreateSaveUiModel(
                        category = MarketItemCategory.SNACKS
                    )
                )
            )
        }
        ItemCreateContent(
            uiState = uiState,
            onSaveClick = {},
            onErrorDismiss = {
                uiState = uiState.copy(
                    errorRes = null,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketListItemAddContentInvalidAllPreview() {
    CheckMarketTheme {
        var uiState by remember {
            mutableStateOf(
                ItemCreateState(
                    invalidFields = listOf(
                        ItemCreateFields.NAME,
                        ItemCreateFields.PRICE,
                        ItemCreateFields.QUANTITY
                    ),
                    item = ItemCreateSaveUiModel(
                        category = MarketItemCategory.SNACKS
                    )
                )
            )
        }
        ItemCreateContent(
            uiState = uiState,
            onSaveClick = {},
            onErrorDismiss = {
                uiState = uiState.copy(
                    errorRes = null,
                )
            }
        )
    }
}
