package io.github.joaogouveia89.checkmarket.marketList.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.marketList.data.repository.MarketListRepositoryImpl
import io.github.joaogouveia89.checkmarket.marketList.data.source.MarketListDataSourceImpl
import io.github.joaogouveia89.checkmarket.marketList.domain.repository.MarketListRepository
import io.github.joaogouveia89.checkmarket.marketList.domain.source.MarketListDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarketListModule {
    @Provides
    @Singleton
    fun provideMarketListDataSource(marketItemDao: MarketItemDao): MarketListDataSource =
        MarketListDataSourceImpl(marketItemDao)

    @Provides
    @Singleton
    fun provideMarketListRepository(marketListDataSource: MarketListDataSource): MarketListRepository =
        MarketListRepositoryImpl(marketListDataSource)
}