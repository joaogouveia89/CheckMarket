package io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase

import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.model.ItemAddSaveUiModel
import kotlinx.coroutines.flow.Flow

interface ItemAddUseCase {
    suspend fun saveItem(item: ItemAddSaveUiModel): Flow<FetchItemsStatus>
    suspend fun fetchItems(): Flow<FetchItemsStatus>

    suspend fun evaluateQuerySimilarity(
        items: List<MatchItem>,
        query: String
    ): Flow<QuerySimilarityEvaluationStatus>
}