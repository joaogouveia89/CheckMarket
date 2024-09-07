package io.github.joaogouveia89.checkmarket.marketListItemCreate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.marketListItemCreate.data.repository.ItemCreateRepositoryImpl
import io.github.joaogouveia89.checkmarket.marketListItemCreate.data.source.ItemCreateLocalDataSourceImpl
import io.github.joaogouveia89.checkmarket.marketListItemCreate.data.usecase.ItemCreateUseCaseImpl
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.source.ItemCreateLocalDataSource
import io.github.joaogouveia89.checkmarket.marketListItemCreate.domain.usecase.ItemCreateUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarketListItemCreateModule {

    @Provides
    @Singleton
    fun provideItemCreateLocalDataSource(marketItemDao: MarketItemDao): ItemCreateLocalDataSource =
        ItemCreateLocalDataSourceImpl(marketItemDao)

    @Provides
    @Singleton
    fun provideItemCreateRepository(localDataSource: ItemCreateLocalDataSource): ItemCreateRepository =
        ItemCreateRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideItemCreateUseCase(repository: ItemCreateRepository): ItemCreateUseCase =
        ItemCreateUseCaseImpl(repository)

}