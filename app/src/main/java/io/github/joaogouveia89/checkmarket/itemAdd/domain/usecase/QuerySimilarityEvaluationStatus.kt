package io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase

import io.github.joaogouveia89.checkmarket.itemAdd.model.MatchItem

sealed class QuerySimilarityEvaluationStatus {
    data object Idle : QuerySimilarityEvaluationStatus()
    data object Loading : QuerySimilarityEvaluationStatus()
    data class Success(val items: List<MatchItem>) : QuerySimilarityEvaluationStatus()
}