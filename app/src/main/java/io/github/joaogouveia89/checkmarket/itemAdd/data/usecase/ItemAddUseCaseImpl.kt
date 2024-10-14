package io.github.joaogouveia89.checkmarket.itemAdd.data.usecase

import io.github.joaogouveia89.checkmarket.core.status.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.itemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase.QuerySimilarityEvaluationStatus
import io.github.joaogouveia89.checkmarket.itemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.model.ItemAddSaveUiModel
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.model.asMarketItem
import io.github.joaogouveia89.checkmarket.itemAdd.presentation.state.ItemAddSaveItemState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemAddUseCaseImpl @Inject constructor(
    private val repository: ItemAddRepository,
    private val iOScope: CoroutineScope
) : ItemAddUseCase {
    override suspend fun saveItem(item: ItemAddSaveUiModel): Flow<ItemAddSaveItemState> =
        repository.saveItem(item.asMarketItem())

    override suspend fun fetchItems(): Flow<FetchItemsStatus> =
        repository.fetchItems()

    override suspend fun evaluateQuerySimilarity(
        items: List<MatchItem>,
        query: String
    ): Flow<QuerySimilarityEvaluationStatus> = flow {
        emit(QuerySimilarityEvaluationStatus.Loading)

        val jobs = mutableListOf<Deferred<MatchItem?>>()
        for (item in items) {
            jobs.add(
                iOScope.async {
                    val similarity = LevenshteinDistance(item.name, query).run { similarity() }
                    if (similarity > 0.3) {
                        item
                    } else null
                }
            )
        }

        emit(
            QuerySimilarityEvaluationStatus.Success(
                jobs.awaitAll().filterNotNull()
            )
        )
    }
}