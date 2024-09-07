package io.github.joaogouveia89.checkmarket.marketListItemAdd.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.marketListItemAdd.data.repository.ItemAddRepositoryImpl
import io.github.joaogouveia89.checkmarket.marketListItemAdd.data.source.ItemAddLocalDataSourceImpl
import io.github.joaogouveia89.checkmarket.marketListItemAdd.data.usecase.ItemAddUseCaseImpl
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.repository.ItemAddRepository
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source.ItemAddLocalDataSource
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.usecase.ItemAddUseCase
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
    fun provideItemAddUseCase(repository: ItemAddRepository): ItemAddUseCase =
        ItemAddUseCaseImpl(repository)

}