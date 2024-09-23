package io.github.joaogouveia89.checkmarket.marketListItemAdd.data.usecase

import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.FetchItemsStatus
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
import io.github.joaogouveia89.checkmarket.marketListItemAdd.model.MatchItem
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.model.ItemAddSaveUiModel
import io.github.joaogouveia89.checkmarket.marketListItemAdd.presentation.model.asMarketItem
import kotlinx.coroutines.CoroutineDispatcher
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
    override suspend fun saveItem(item: ItemAddSaveUiModel): Flow<FetchItemsStatus> =
        repository.saveItem(item.asMarketItem())

    override suspend fun fetchItems(): Flow<FetchItemsStatus> =
        repository.fetchItems()

    override suspend fun evaluateQuerySimilarity(items: List<MatchItem>, query: String): List<MatchItem>{
        val jobs = mutableListOf<Deferred<MatchItem?>>()
        for(item in items){
            jobs.add(
                iOScope.async {
                    val similarity = LevenshteinDistance(item.name, query).run { similarity() }
                    if(similarity > 0.5){
                        item
                    }else null
                }
            )
        }

        return jobs
            .awaitAll()
            .mapNotNull { it }
    }
}