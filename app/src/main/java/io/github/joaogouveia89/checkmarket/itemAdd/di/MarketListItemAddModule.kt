package io.github.joaogouveia89.checkmarket.itemAdd.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.itemAdd.data.repository.ItemAddRepositoryImpl
import io.github.joaogouveia89.checkmarket.itemAdd.data.source.ItemAddLocalDataSourceImpl
import io.github.joaogouveia89.checkmarket.itemAdd.data.usecase.ItemAddUseCaseImpl
import io.github.joaogouveia89.checkmarket.itemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.itemAdd.domain.source.ItemAddLocalDataSource
import io.github.joaogouveia89.checkmarket.itemAdd.domain.usecase.ItemAddUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MarketListItemAddModule {
    @Provides
    @Singleton
    fun provideItemAddLocalDataSource(marketItemDao: MarketItemDao): ItemAddLocalDataSource =
        ItemAddLocalDataSourceImpl(marketItemDao)

    @Provides
    @Singleton
    fun provideItemAddRepository(localDataSource: ItemAddLocalDataSource): ItemAddRepository =
        ItemAddRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideItemAddUseCase(
        repository: ItemAddRepository,
        scope: CoroutineScope
    ): ItemAddUseCase =
        ItemAddUseCaseImpl(repository, scope)

}