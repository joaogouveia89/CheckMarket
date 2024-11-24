package io.github.joaogouveia89.checkmarket.itemEdit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.joaogouveia89.checkmarket.core.data.local.dao.MarketItemDao
import io.github.joaogouveia89.checkmarket.itemEdit.data.source.ItemEditLocalDataSourceImpl
import io.github.joaogouveia89.checkmarket.itemEdit.domain.source.ItemEditLocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarketListItemEditModule {

    @Provides
    @Singleton
    fun provideItemEditLocalDataSource(marketItemDao: MarketItemDao): ItemEditLocalDataSource =
        ItemEditLocalDataSourceImpl(marketItemDao)
}