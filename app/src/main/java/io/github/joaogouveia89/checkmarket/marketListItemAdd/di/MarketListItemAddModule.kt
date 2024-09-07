package io.github.joaogouveia89.checkmarket.marketListItemAdd.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.marketListItemAdd.data.source.ItemAddLocalDataSourceImpl
import io.github.joaogouveia89.checkmarket.marketListItemAdd.domain.source.ItemAddLocalDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MarketListItemAddModule {
    @Provides
    @Singleton
    fun provideItemAddLocalDataSource(marketItemDao: MarketItemDao): ItemAddLocalDataSource =
        ItemAddLocalDataSourceImpl(marketItemDao)
}