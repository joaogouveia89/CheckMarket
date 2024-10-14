package io.github.joaogouveia89.checkmarket.itemCreate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.itemCreate.data.repository.ItemCreateRepositoryImpl
import io.github.joaogouveia89.checkmarket.itemCreate.data.source.ItemCreateLocalDataSourceImpl
import io.github.joaogouveia89.checkmarket.itemCreate.data.usecase.ItemCreateUseCaseImpl
import io.github.joaogouveia89.checkmarket.itemCreate.domain.repository.ItemCreateRepository
import io.github.joaogouveia89.checkmarket.itemCreate.domain.source.ItemCreateLocalDataSource
import io.github.joaogouveia89.checkmarket.itemCreate.domain.usecase.ItemCreateUseCase
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