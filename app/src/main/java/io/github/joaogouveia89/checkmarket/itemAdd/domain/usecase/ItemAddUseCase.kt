package io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.itemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.model.ItemAddSaveUiModel
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.state.ItemAddSaveItemState
import kotlinx.coroutines.flow.Flow

interface ItemAddUseCase {
    suspend fun saveItem(item: ItemAddSaveUiModel): Flow<ItemAddSaveItemState>
    suspend fun fetchItems(): Flow<FetchItemsStatus>

    suspend fun evaluateQuerySimilarity(
        items: List<MatchItem>,
        query: String
    ): Flow<QuerySimilarityEvaluationStatus>
}